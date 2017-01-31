package com.example.raza.networkrequestmanagment.network.config;

/**
 * Created by Muzammil on 26/01/2017.
 */

public class NetworkConfig
{
    private int timeout = 30000;
    private int retryCount = 3;

    // determine exponential time set to socket for every retry attempt
    // timeout = timeout + (timeout x Back Off Multiplier);
    private int backOffMulti = 1;

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
