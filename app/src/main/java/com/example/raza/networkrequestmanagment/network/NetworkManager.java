package com.example.raza.networkrequestmanagment.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.async.MultipartRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.async.SubmitRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.dto.MultipartNetworkDataObject;
import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkManager {

    public static int REQUEST_TIMEOUT = 15000;
    public static int RETRY_COUNT = 0;


    Context mContext;

    private static NetworkManager mNetworkManager;
    private static AsyncTask currentNetworkRequest;

    public NetworkManager(Context context, int REQUEST_TIMEOUT, int RETRY_COUNT) {
        this.REQUEST_TIMEOUT = REQUEST_TIMEOUT;
        this.RETRY_COUNT = RETRY_COUNT;
        mContext = context;
    }

    public static NetworkManager getInstance(Context mContext) {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager(mContext, REQUEST_TIMEOUT, RETRY_COUNT);

        return mNetworkManager;
    }

    public static NetworkManager getInstance(Context context, int requestTimeout, int retryCount) {
        if (mNetworkManager == null)
            mNetworkManager = new NetworkManager(context, requestTimeout, retryCount);

        return mNetworkManager;
    }

    public void networkSubmitRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, String methord, Map<String, String> dataToSend, Map<String, String> headerParams) throws IOException {
        NetworkDataObject mNetDataObj = new NetworkDataObject(urlPath, methord, dataToSend, headerParams);

        SubmitRequestAsyncTask mNetworkSubmitRequestAsync = new SubmitRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkSubmitRequestAsync;
        mNetworkSubmitRequestAsync.execute(mNetDataObj);

    }

    public void cancelNetworkRequest() {
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

    public void networkMultipartRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, Map<String, String> params, String methord, Map<String, String> dataToSend, Map<String, String> headerParams, String fileName, String filePath, String charSet) throws IOException {
        Map<String, String> files = new HashMap<>();
        files.put(fileName, filePath);
        MultipartNetworkDataObject mNetDataObj = new MultipartNetworkDataObject(urlPath, methord, dataToSend, headerParams, files, charSet);

        MultipartRequestAsyncTask mNetworkMultipartRequestAsync = new MultipartRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkMultipartRequestAsync;
        mNetworkMultipartRequestAsync.execute(mNetDataObj);

    }

}
