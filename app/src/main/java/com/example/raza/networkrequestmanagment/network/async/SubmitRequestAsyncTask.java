package com.example.raza.networkrequestmanagment.network.async;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.config.NetworkConfig;
import com.example.raza.networkrequestmanagment.network.constants.HttpResponseCode;
import com.example.raza.networkrequestmanagment.network.constants.NetworkHttpMethods;
import com.example.raza.networkrequestmanagment.network.constants.NetworkResponseCodes;
import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;
import com.example.raza.networkrequestmanagment.network.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 * Edited by MuzammilSaeed on 01/26/2017
 */
public class SubmitRequestAsyncTask extends AsyncTask<NetworkDataObject, Void, SubmitRequestAsyncTask.ResponseObject>
{
    class ResponseObject
    {
        int responseCode;
        String responseMessage;

        public ResponseObject(int code, String response)
        {
            this.responseCode = code;
            this.responseMessage = response;
        }
    };

    private NetworkManagerInterface mNetworkManagerInterface;
    private boolean isRequestSucessful = true;
    private HttpURLConnection networkConnection;
    private String response;
    private String encodedStr;

    public SubmitRequestAsyncTask(NetworkManagerInterface mNetworkManagerInterface) {
        this.mNetworkManagerInterface = mNetworkManagerInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected SubmitRequestAsyncTask.ResponseObject doInBackground(NetworkDataObject... networkDataObjects)
    {
        int responseCode = -1;

        if(networkDataObjects[0].getNetworkMethord() == NetworkHttpMethods.POST)
        {
            //Encoded String - we will have to encode string by our custom method (Very easy)

            Map<String, String> headerParams = networkDataObjects[0].getHeaderParams();
            int headerParamsSize = headerParams.size();

            if ( headerParams != null && headerParamsSize > 0)
                encodedStr = NetworkUtils.getEncodedData(networkDataObjects[0].getDataToSendRaw()); // encode Map<>
            else if ( headerParams != null && headerParamsSize <= 0)
                encodedStr = NetworkUtils.getEncodedData(networkDataObjects[0].getDataToSend()); // encode String
        }

        //Will be used if we want to read some data from server
        BufferedReader reader = null;

        //Connection Handling
        try
        {
            //Converting address String to URL
            URL url = new URL(networkDataObjects[0].getUrl());

            networkConnection = MakeRequest(
                    url,
                    networkDataObjects[0].getNetworkMethord(),
                    networkDataObjects[0].getHeaderParams(),
                    networkDataObjects[0].getNetworkConfig(),
                    1);

            // something went wrong....it has nothing to do with our code (e.g. INTERNET is down)
            if(networkConnection == null)
                return new ResponseObject(-1, "Uknown Error");

            //Data Read Procedure - Basically reading the data comming line by line
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(networkConnection.getInputStream()));

            // Get response against our request (line-by-line)
            while ((response = reader.readLine()) != null)
                sb.append(response + "\n");

            response = sb.toString();

            //Just check to the values received in Logcat
            Log.i("custom_check", "The values received in the store part are as follows:");
            Log.i("custom_check", response);
        }
        catch (Exception e)
        {
            isRequestSucessful = false;
            response = e.toString();
            e.printStackTrace();
        }
        finally
        {
            try
            {responseCode = networkConnection.getResponseCode();} catch (Exception ex) {}

            isRequestSucessful = true;
            if (reader != null) {
                try {
                    reader.close();     //Closing the
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            networkConnection.disconnect();
        }

        //Same return null, but if you want to return the read string (stored in line)
        //then change the parameters of AsyncTask and return that type, by converting
        //the string - to say JSON or user in your case
        return new ResponseObject(responseCode, response);
    }

    private String getNetworkMethodMapped(NetworkHttpMethods method)
    {
        switch (method)
        {
            case GET:
                return "GET";
            case POST:
                return "POST";
            case HEAD:
                return "HEAD";
            case PUT:
                return "PUT";
            case DELETE:
                return "DELETE";
            case OPTIONS:
                return "OPTIONS";
            case CONNECT:
                return "CONNECT";
            case PATCH:
                return "PATCH";
        }

        return "UNKNOWN";
    }

    private HttpURLConnection MakeRequest(
            URL url,
            NetworkHttpMethods method,
            Map<String, String> headerParams,
            NetworkConfig config,
            int currentRetryIndex)
    {
        HttpURLConnection httpURLConnection = null;
        int requestRetryCount = config.getRetryCount();

        try
        {
            // mCurrentRetryCount is static
            if(currentRetryIndex <= requestRetryCount)
            {
                //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
                httpURLConnection = (HttpURLConnection) url.openConnection();

                // Network Method e.g POST
                httpURLConnection.setRequestMethod(getNetworkMethodMapped(method));

                if (headerParams != null && (method == NetworkHttpMethods.POST))
                {
                    httpURLConnection.setDoOutput(true);
                    NetworkUtils.setRequestHeader(httpURLConnection, headerParams);

                    if (encodedStr.trim().length() > 0) {
                        OutputStreamWriter writer = new OutputStreamWriter(httpURLConnection.getOutputStream());
                        writer.write(encodedStr);
                        writer.flush();
                    }
                }

                // Network Timeout e.g 5000 milliseconds
                httpURLConnection.setConnectTimeout(config.getTimeout());

                // Get response code (against whatever request was made e.g GET/POST)
                int responseCode = httpURLConnection.getResponseCode();
                String respMessage = httpURLConnection.getResponseMessage();

                if (responseCode == HttpURLConnection.HTTP_OK /*200*/)
                    return httpURLConnection;
            }
        }
        catch (SocketTimeoutException ste)
        {
            MakeRequest(url, method, headerParams, config, ++currentRetryIndex);
        }
        catch (Exception ex)
        {
            MakeRequest(url, method, headerParams, config, ++currentRetryIndex);
        }

        return httpURLConnection;
    }

    @Override
    protected void onPostExecute(ResponseObject ro)
    {
        super.onPostExecute(ro);

        HttpResponseCode code = NetworkResponseCodes.GetHttpResponse(ro.responseCode);

        if (isRequestSucessful)
            mNetworkManagerInterface.onSuccess(code, ro.responseMessage);
        else
            mNetworkManagerInterface.onFailure(code, ro.responseMessage);
    }

    public void closeHttpURLConnection() {
        if (networkConnection != null)
            networkConnection.disconnect();
    }
}
