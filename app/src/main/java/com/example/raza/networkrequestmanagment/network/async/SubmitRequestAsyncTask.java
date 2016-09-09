package com.example.raza.networkrequestmanagment.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;
import com.example.raza.networkrequestmanagment.network.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class SubmitRequestAsyncTask extends AsyncTask<NetworkDataObject, Void, String> {

    private NetworkManagerInterface mNetworkManagerInterface;
    private boolean isRequestSucessful = true;
    private HttpURLConnection networkConnection;
    private String response;


    public SubmitRequestAsyncTask(NetworkManagerInterface mNetworkManagerInterface) {
        this.mNetworkManagerInterface = mNetworkManagerInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(NetworkDataObject... networkDataObjects) {
        //Encoded String - we will have to encode string by our custom method (Very easy)
        String encodedStr = NetworkUtils.getEncodedData(networkDataObjects[0].getDataToSend());

        //Will be used if we want to read some data from server
        BufferedReader reader = null;

        //Connection Handling
        try {
            //Converting address String to URL
            URL url = new URL(networkDataObjects[0].getUrl());
            //Opening the connection (Not setting or using CONNECTION_TIMEOUT)
            networkConnection = (HttpURLConnection) url.openConnection();

            // Network Method e.g POST
            networkConnection.setRequestMethod(networkDataObjects[0].getNetworkMethord());

            // set header
            if (networkDataObjects[0].getHeaderParams() != null)
                NetworkUtils.setRequestHeader(networkConnection, networkDataObjects[0].getHeaderParams());

            //To enable inputting values using POST method
            //(Basically, after this we can write the dataToSend to the body of POST method)
            networkConnection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(networkConnection.getOutputStream());
            //Writing dataToSend to outputstreamwriter
            writer.write(encodedStr);
            //Sending the data to the server - This much is enough to send data to server
            //But to read the response of the server, you will have to implement the procedure below
            writer.flush();

            //Data Read Procedure - Basically reading the data comming line by line
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(networkConnection.getInputStream()));

            while ((response = reader.readLine()) != null) { //Read till there is something available
                sb.append(response + "\n");     //Reading and saving line by line - not all at once
            }
            response = sb.toString();           //Saving complete data received in string, you can do it differently

            //Just check to the values received in Logcat
            Log.i("custom_check", "The values received in the store part are as follows:");
            Log.i("custom_check", response);

        } catch (Exception e) {
            isRequestSucessful = false;
            response = e.toString();
            e.printStackTrace();
        } finally {
            isRequestSucessful = true;
            if (reader != null) {
                try {
                    reader.close();     //Closing the
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Same return null, but if you want to return the read string (stored in line)
        //then change the parameters of AsyncTask and return that type, by converting
        //the string - to say JSON or user in your case
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (isRequestSucessful)
            mNetworkManagerInterface.onSuccess(s);
        else
            mNetworkManagerInterface.onFailure(s);
    }

    public void closeHttpURLConnection() {
        if (networkConnection != null)
            networkConnection.disconnect();
    }
}
