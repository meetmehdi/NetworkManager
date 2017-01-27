package com.example.raza.networkrequestmanagment.network.dto;

import com.example.raza.networkrequestmanagment.network.config.NetworkConfig;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 * * Edited by MuzammilSaeed on 01/26/2017
 */
public class NetworkDataObject implements Serializable
{
    private String url;
    private NetworkConfig networkConfig;
    private String networkMethord;
    private Map<String, String> dataToSend;
    private Map<String, String> headerParams;

    public NetworkDataObject(String url, String networkMethord, Map<String, String> dataToSend) {
        this.url = url;
        this.networkMethord = networkMethord;
        this.dataToSend = dataToSend;
    }

    public NetworkDataObject(String url, String networkMethord, Map<String, String> dataToSend, Map<String, String> headerParams) {
        this.url = url;
        this.networkMethord = networkMethord;
        this.dataToSend = dataToSend;
        this.headerParams = headerParams;
    }

    public NetworkDataObject(String url, NetworkConfig networkConfig, String networkMethord, Map<String, String> dataToSend, Map<String, String> headerParams)
    {
        this.url = url;
        this.networkConfig = networkConfig;
        this.networkMethord = networkMethord;
        this.dataToSend = dataToSend;
        this.headerParams = headerParams;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public NetworkConfig getNetworkConfig() {
        return networkConfig;
    }

    public void setNetworkConfig(NetworkConfig networkConfig) {
        this.networkConfig = networkConfig;
    }

    public String getNetworkMethord() {
        return networkMethord;
    }

    public void setNetworkMethord(String networkMethord) {
        this.networkMethord = networkMethord;
    }

    public Map<String, String> getDataToSend() {
        return dataToSend;
    }

    public void setDataToSend(Map<String, String> dataToSend) {
        this.dataToSend = dataToSend;
    }

    public Map<String, String> getHeaderParams() {
        return headerParams;
    }

    public void setHeaderParams(Map<String, String> headerParams) {
        this.headerParams = headerParams;
    }
}
