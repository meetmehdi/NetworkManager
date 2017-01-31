package com.example.raza.networkrequestmanagment;

import android.app.Application;
import android.content.Context;

import com.example.raza.networkrequestmanagment.network.NetworkManager;
import com.example.raza.networkrequestmanagment.network.config.NetworkConfig;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkRequestManagment extends Application {

    public static NetworkManager mNetManager;
    public static Context mContext;


    @Override
    public void onCreate()
    {
        super.onCreate();

        mContext = getApplicationContext();

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setTimeout(30000); // 30sec (read request timeout, connection time out)
        networkConfig.setRetryCount(3); // 3

//        mNetManager = NetworkManager.getInstance(mContext, NetworkManager.REQUEST_TIMEOUT, NetworkManager.RETRY_COUNT);
        mNetManager = NetworkManager.getInstance(mContext, networkConfig);
    }
}
