package com.example.raza.networkrequestmanagment.network.dto;

/**
 * Created by SyedRazaMehdiNaqvi on 10/10/2016.
 */
public class APIFileParameter {
    String key;
    String path;

    public APIFileParameter() {
    }

    public APIFileParameter(String key, String path) {
        this.key = key;
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
