package com.example.raza.networkrequestmanagment.network.interfaces;

import com.example.raza.networkrequestmanagment.network.constants.HttpResponseCode;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 * Edited by MuzammilSaeed on 01/26/2017
 */
public interface NetworkManagerInterface
{

    void onSuccess(HttpResponseCode code, String networkResponse);

    void onFailure(HttpResponseCode code, String networkResponse);

}
