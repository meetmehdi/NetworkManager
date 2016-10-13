package com.example.raza.networkrequestmanagment.network.dto;

import java.util.ArrayList;

/**
 * Created by SyedRazaMehdiNaqvi on 10/10/2016.
 */
public class RequestParamsMultipart {
    ArrayList<APIParameter> mParameters;
    ArrayList<APIFileParameter> mFileParameters;

    public RequestParamsMultipart() {
    }

    public RequestParamsMultipart(ArrayList<APIParameter> mParameters, ArrayList<APIFileParameter> mFileParameters) {
        this.mParameters = mParameters;
        this.mFileParameters = mFileParameters;
    }

    public ArrayList<APIParameter> getmParameters() {
        return mParameters;
    }

    public void setmParameters(ArrayList<APIParameter> mParameters) {
        this.mParameters = mParameters;
    }

    public ArrayList<APIFileParameter> getmFileParameters() {
        return mFileParameters;
    }

    public void setmFileParameters(ArrayList<APIFileParameter> mFileParameters) {
        this.mFileParameters = mFileParameters;
    }
}
