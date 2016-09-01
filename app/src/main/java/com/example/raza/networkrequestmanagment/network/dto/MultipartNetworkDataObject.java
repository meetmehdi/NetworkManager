package com.example.raza.networkrequestmanagment.network.dto;

import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/18/2016.
 */
public class MultipartNetworkDataObject {
    private String url;
    private String networkMethord;
    private Map<String, String> dataToSend;
    private Map<String, String> headerParams;
    private Map<String, String> file;
    private String charSet; // UTF-8

    public MultipartNetworkDataObject(String url, String networkMethord, Map<String, String> dataToSend, Map<String, String> headerParams, Map<String, String> file, String charSet) {
        this.url = url;
        this.networkMethord = networkMethord;
        this.dataToSend = dataToSend;
        this.headerParams = headerParams;
        this.file = file;
        this.charSet = charSet;
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

    public Map<String, String> getFile() {
        return file;
    }

    public void setFile(Map<String, String> file) {
        this.file = file;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }
}
