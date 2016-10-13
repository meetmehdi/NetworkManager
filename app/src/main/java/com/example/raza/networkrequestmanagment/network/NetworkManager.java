package com.example.raza.networkrequestmanagment.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.raza.networkrequestmanagment.network.async.MultipartRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.async.SubmitRequestAsyncTask;
import com.example.raza.networkrequestmanagment.network.dto.MultipartNetworkDataObject;
import com.example.raza.networkrequestmanagment.network.dto.NetworkDataObject;
import com.example.raza.networkrequestmanagment.network.dto.RequestParams;
import com.example.raza.networkrequestmanagment.network.dto.RequestParamsMultipart;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkManager {

    public static int REQUEST_TIMEOUT = 30000;
    public static int REQUEST_READ_TIMEOUT = 30000;
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

    public void networkSubmitRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, String methord, Class classOfT, Map<String, String> dataToSend, Map<String, String> headerParams) throws IOException {
        NetworkDataObject mNetDataObj = new NetworkDataObject(urlPath, methord, dataToSend, headerParams);
        SubmitRequestAsyncTask mNetworkSubmitRequestAsync = new SubmitRequestAsyncTask(mNetworkManagerInterface, classOfT);
        currentNetworkRequest = mNetworkSubmitRequestAsync;
        mNetworkSubmitRequestAsync.execute(mNetDataObj);
    }

    public void networkSubmitRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, String methord, RequestParams dataToSend, RequestParams headerParams) throws IOException {
        NetworkDataObject mNetDataObj = new NetworkDataObject(urlPath, methord, getAPIParams(dataToSend), getAPIParams(headerParams));
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

    public void networkMultipartRequest(NetworkManagerInterface mNetworkManagerInterface, String urlPath, String methord, RequestParamsMultipart dataToSend, RequestParams headerParams, String charSet) throws IOException {
        MultipartNetworkDataObject mNetDataObj = new MultipartNetworkDataObject(urlPath, methord, getAPIParams(dataToSend), getAPIParams(headerParams), getAPIFileParams(dataToSend), charSet);
        MultipartRequestAsyncTask mNetworkMultipartRequestAsync = new MultipartRequestAsyncTask(mNetworkManagerInterface);
        currentNetworkRequest = mNetworkMultipartRequestAsync;
        mNetworkMultipartRequestAsync.execute(mNetDataObj);

    }

    public Map<String, String> getAPIParams(RequestParams mRequestParams) {
        HashMap<String, String> mParams = new HashMap<>();
        if (mRequestParams != null && mRequestParams.getmParameters().size() > 0) {
            for (int i = 0; i < mRequestParams.getmParameters().size(); i++) {
                mParams.put(mRequestParams.getmParameters().get(i).getKey(), mRequestParams.getmParameters().get(i).getValue());
            }
        }
        return mParams;
    }

    public Map<String, String> getAPIParams(RequestParamsMultipart mRequestParams) {
        HashMap<String, String> mParams = new HashMap<>();
        if (mRequestParams != null && mRequestParams.getmParameters().size() > 0) {
            for (int i = 0; i < mRequestParams.getmParameters().size(); i++) {
                mParams.put(mRequestParams.getmParameters().get(i).getKey(), mRequestParams.getmParameters().get(i).getValue());
            }
        }
        return mParams;
    }

    public Map<String, String> getAPIFileParams(RequestParamsMultipart mRequestParams) {
        HashMap<String, String> mParams = new HashMap<>();
        if (mRequestParams != null && mRequestParams.getmFileParameters().size() > 0) {
            for (int i = 0; i < mRequestParams.getmFileParameters().size(); i++) {
                mParams.put(mRequestParams.getmFileParameters().get(i).getKey(), mRequestParams.getmFileParameters().get(i).getPath());
            }
        }
        return mParams;
    }
}
