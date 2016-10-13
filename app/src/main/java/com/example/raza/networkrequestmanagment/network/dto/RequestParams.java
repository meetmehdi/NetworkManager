package com.example.raza.networkrequestmanagment.network.dto;

import java.util.ArrayList;

/**
 * Created by SyedRazaMehdiNaqvi on 10/10/2016.
 */
public class RequestParams {
    ArrayList<APIParameter> mParameters;

    public RequestParams() {
        this.mParameters = new ArrayList<APIParameter>();
    }

    public RequestParams(ArrayList<APIParameter> mParameters) {
        this.mParameters = mParameters;
    }

    public ArrayList<APIParameter> getmParameters() {
        return mParameters;
    }

    public void setmParameters(ArrayList<APIParameter> mParameters) {
        this.mParameters = mParameters;
    }
}
