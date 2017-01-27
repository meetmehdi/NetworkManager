package com.example.raza.networkrequestmanagment.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.config.NetworkConfig;
import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;
import com.example.raza.networkrequestmanagment.network.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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

        //Encoded String - we will have to encode string by our custom method (Very easy)
        encodedStr = NetworkUtils.getEncodedData(networkDataObjects[0].getDataToSend());

        //Will be used if we want to read some data from server
        BufferedReader reader = null;

        //Connection Handling
        try
        {
            //Converting address String to URL
            URL url = new URL(networkDataObjects[0].getUrl());

//            //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
//            networkConnection = (HttpURLConnection) url.openConnection();
//
//            // Network Method e.g POST
//            networkConnection.setRequestMethod(networkDataObjects[0].getNetworkMethord());
//
//            // Network Timeout e.g 5000 milliseconds
//            networkConnection.setConnectTimeout(networkDataObjects[0].getTimeout());
//
//            responseCode = networkConnection.getResponseCode();

            networkConnection = MakeRequest(
                    url,
                    networkDataObjects[0].getNetworkMethord(),
                    networkDataObjects[0].getHeaderParams(),
                    networkDataObjects[0].getNetworkConfig());

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

    private HttpURLConnection MakeRequest(URL url, String method, Map<String, String> headerParams, NetworkConfig config)
    {

        HttpURLConnection htp_url_connection = null;
        int count = 1;
        int retry_count = config.getTimeout();

        try
        {
            //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
            htp_url_connection = (HttpURLConnection) url.openConnection();

            // Network Method e.g POST
            htp_url_connection.setRequestMethod(method);

            if (headerParams != null && method.trim().toLowerCase().equals("post"))
            {
                htp_url_connection.setDoOutput(true);
                NetworkUtils.setRequestHeader(htp_url_connection, headerParams);

                // We are gonna write "dataToSend" to the body of POST request, using POST method
                if(encodedStr.trim().length() > 0)
                {
                    OutputStreamWriter writer = new OutputStreamWriter(htp_url_connection.getOutputStream());
                    writer.write(encodedStr);
                    writer.flush();
                }
            }

            // Network Timeout e.g 5000 milliseconds
            htp_url_connection.setConnectTimeout(config.getTimeout());

            // Get response code (against whatever request was made e.g GET/POST)
            int responseCode = htp_url_connection.getResponseCode();

            if(responseCode == 200)
                return htp_url_connection;

            // our request was not successfull.... try again "retry_count" times
            while(count < retry_count)
            {
                count++;

                htp_url_connection = (HttpURLConnection) url.openConnection();
                htp_url_connection.setRequestMethod(method);

                if (headerParams != null && method.trim().toLowerCase().equals("post"))
                {
                    htp_url_connection.setDoOutput(true);
                    NetworkUtils.setRequestHeader(htp_url_connection, headerParams);
                    if(encodedStr.trim().length() > 0)
                    {
                        OutputStreamWriter writer = new OutputStreamWriter(htp_url_connection.getOutputStream());
                        writer.write(encodedStr);
                        writer.flush();
                    }
                }

                htp_url_connection.setConnectTimeout(config.getTimeout());
                responseCode = htp_url_connection.getResponseCode();

                if(responseCode == 200)
                    return htp_url_connection;
            }
        }
        catch (Exception ex)
        {
            String str = ex.getMessage();
            String str2 = ex.toString();

            int a = 10;
            int b = 25;
            int rsult = a + b;
        }

        return htp_url_connection;
    }

    @Override
    protected void onPostExecute(ResponseObject ro) {
        super.onPostExecute(ro);
        if (isRequestSucessful)
            mNetworkManagerInterface.onSuccess(ro.responseCode, ro.responseMessage);
        else
            mNetworkManagerInterface.onFailure(ro.responseCode, ro.responseMessage);
    }

    public void closeHttpURLConnection() {
        if (networkConnection != null)
            networkConnection.disconnect();
    }
}
