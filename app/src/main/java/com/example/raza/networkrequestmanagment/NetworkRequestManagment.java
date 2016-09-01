package com.example.raza.networkrequestmanagment;

import android.app.Application;
import android.content.Context;

import com.example.raza.networkrequestmanagment.network.NetworkManager;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkRequestManagment extends Application {

    public static NetworkManager mNetManager;
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mNetManager = NetworkManager.getInstance(mContext, NetworkManager.REQUEST_TIMEOUT, NetworkManager.RETRY_COUNT);

    }
}
