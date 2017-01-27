package com.example.raza.networkrequestmanagment.network.interfaces;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 * Edited by MuzammilSaeed on 01/26/2017
 */
public interface NetworkManagerInterface {

    void onSuccess(int responseCode, String networkResponse);

    void onFailure(int responseCode, String networkResponse);

}
