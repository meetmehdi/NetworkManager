package com.example.raza.networkrequestmanagment.network.dto;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkDataObject implements Serializable {

    private String url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
