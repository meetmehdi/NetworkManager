package com.example.raza.networkrequestmanagment.network.dto;

/**
 * Created by SyedRazaMehdiNaqvi on 10/10/2016.
 */
public class APIParameter {
    String key;
    String value;

    public APIParameter() {
    }

    public APIParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
