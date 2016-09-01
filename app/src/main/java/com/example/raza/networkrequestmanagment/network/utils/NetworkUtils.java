package com.example.raza.networkrequestmanagment.network.utils;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by SyedRazaMehdiNaqvi on 8/17/2016.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static String getEncodedData(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();

        if (data == null)
            return sb.toString();
        for (String key : data.keySet()) {
            String value = null;
            try {
                value = URLEncoder.encode(data.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (sb.length() > 0)
                sb.append("&");

            sb.append(key + "=" + value);
        }
        return sb.toString();
    }

    public static void setRequestHeader(HttpURLConnection mURLConnection, Map<String, String> headerParam) {
        for (Map.Entry<String, String> entry : headerParam.entrySet()) {
            mURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    public static String getGetURL(String baseURL, Map<String, String> params) {
        boolean appendingFirstParam = true;
        String url = baseURL;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (appendingFirstParam) {
                appendingFirstParam = false;
                url = url + "?" + entry.getKey() + "=" + entry.getValue();
            } else {
                url = url + "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        Log.i(TAG, url);
        return url;
    }
}
