package com.example.raza.networkrequestmanagment.network.interfaces;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public interface NetworkManagerInterface {

    void onSuccess(Object networkResponse);

    void onFailure(String networkResponse);

}
