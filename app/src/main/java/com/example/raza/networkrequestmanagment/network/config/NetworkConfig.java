package com.example.raza.networkrequestmanagment.network.config;

/**
 * Created by Muzammil on 26/01/2017.
 */

public class NetworkConfig
{
    private int timeout = 5000;
    private int retryCount = 3;
    private int backoff_mult;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
