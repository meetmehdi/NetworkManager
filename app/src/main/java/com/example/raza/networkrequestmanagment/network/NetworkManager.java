package com.example.raza.networkrequestmanagment.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.async.MultipartRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.async.SubmitRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.config.NetworkConfig;
import com.example.raza.networkrequestmanagment.network.constants.NetworkHttpMethods;
import com.example.raza.networkrequestmanagment.network.dto.MultipartNetworkDataObject;
import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 * Edited by MuzammilSaeed on 01/26/2017
 */
public class NetworkManager
{

    public static int REQUEST_TIMEOUT = 30000;
    public static int REQUEST_READ_TIMEOUT = 30000;
    public static int RETRY_COUNT = 0;

    public static NetworkConfig NETWORK_CONFIG;

    Context mContext;

    private static NetworkManager mNetworkManager;
    private static AsyncTask currentNetworkRequest;

    public NetworkManager(Context context, int REQUEST_TIMEOUT, int RETRY_COUNT)
    {
        this.REQUEST_TIMEOUT = REQUEST_TIMEOUT;
        this.RETRY_COUNT = RETRY_COUNT;
        mContext = context;
    }

    public NetworkManager(Context context, NetworkConfig networkConfig)
    {
        this.NETWORK_CONFIG = networkConfig;
        mContext = context;
    }

    public static NetworkManager getInstance(Context mContext)
    {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager(mContext, REQUEST_TIMEOUT, RETRY_COUNT);

        return mNetworkManager;
    }

    public static NetworkManager getInstance(Context context, int requestTimeout, int retryCount)
    {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager(context, requestTimeout, retryCount);

        return mNetworkManager;
    }

    public static NetworkManager getInstance(Context context, NetworkConfig networkConfig)
    {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager(context, networkConfig);

        return mNetworkManager;
    }

    public void networkSubmitRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, NetworkHttpMethods method, Map<String, String> dataToSend, Map<String, String> headerParams) throws IOException
    {
        NetworkDataObject mNetDataObj = new NetworkDataObject(urlPath, this.NETWORK_CONFIG, method, dataToSend, headerParams);

        SubmitRequestAsyncTask mNetworkSubmitRequestAsync = new SubmitRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkSubmitRequestAsync;
        mNetworkSubmitRequestAsync.execute(mNetDataObj);
    }

    public void SubmitNetworkRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, NetworkHttpMethods method, String dataToSendRaw, Map<String, String> headerParams) throws IOException
    {
        NetworkDataObject mNetDataObj = new NetworkDataObject(urlPath, this.NETWORK_CONFIG, method, dataToSendRaw, headerParams);

        SubmitRequestAsyncTask mNetworkSubmitRequestAsync = new SubmitRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkSubmitRequestAsync;
        mNetworkSubmitRequestAsync.execute(mNetDataObj);
    }

    public void cancelNetworkRequest()
    {
        try {
            SubmitRequestAsyncTask mRequest = (SubmitRequestAsyncTask) currentNetworkRequest;
            mRequest.closeHttpURLConnection();
            if (!mRequest.isCancelled())
                mRequest.cancel(true);
        } catch (Exception e) {
            Log.e(e.getMessage(), e.getMessage());
        }

        try {
            MultipartRequestAsyncTask mRequest = (MultipartRequestAsyncTask) currentNetworkRequest;
            mRequest.closeHttpURLConnection();
            if (!mRequest.isCancelled())
                mRequest.cancel(true);
        } catch (Exception e) {
            Log.e(e.getMessage(), e.getMessage());
        }
    }

    public void networkMultipartRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, Map<String, String> params, String methord, Map<String, String> dataToSend, Map<String, String> headerParams,  Map<String, String> files, String charSet) throws IOException
    {
        MultipartNetworkDataObject mNetDataObj = new MultipartNetworkDataObject(urlPath, methord, dataToSend, headerParams, files, charSet);

        MultipartRequestAsyncTask mNetworkMultipartRequestAsync = new MultipartRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkMultipartRequestAsync;
        mNetworkMultipartRequestAsync.execute(mNetDataObj);

    }
}
