package com.example.raza.networkrequestmanagment.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.raza.networkrequestmanagment.NetworkRequestManagment;
import com.example.raza.networkrequestmanagment.R;
import com.example.raza.networkrequestmanagment.network.constants.NetworkConstants;
import com.example.raza.networkrequestmanagment.network.dto.APIParameter;
import com.example.raza.networkrequestmanagment.network.dto.GovtNotices;
import com.example.raza.networkrequestmanagment.network.dto.RequestParams;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;
import com.example.raza.networkrequestmanagment.network.utils.NetworkUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private final String URL_GET_PARAMS = "your_own_url";
    //    private final String URL_GET_PARAMS = "your_own_url";
    private final String URL_GET = "your_own_url";
    //    private final String URL_GET = "your_own_url";
    private final String URL_POST = "your_own_url";
    private final String URL_POLICE_EXCISE = "your_own_url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testGetRequestWithParams();
//        testPostPoliceExciseRequest();
//        testPostRequest();
//        testGetRequestWithParams();
        testGetRequest();
    }

    private void testGetRequest() {
        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(Object networkResponse) {
                    GovtNotices mGov = (GovtNotices) networkResponse;
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", "<Success>:" + mGov.toString());
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", "<Failure>" + networkResponse);
                }
            }, URL_GET, NetworkConstants.NETWORK_METHORD_GET, GovtNotices.class, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void testGetRequestWithParams() {

        RequestParams mPostRequestParams = new RequestParams();
        mPostRequestParams.getmParameters().add(new APIParameter("facebook_id", "123"));

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("facebook_id", "123");

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(Object networkResponse) {
                    Toast.makeText(getApplicationContext(), "<Success>" + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse.toString());
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", "<Failure>" + networkResponse);
                }
            }, NetworkUtils.getGetURL(URL_GET_PARAMS, mPostRequestParams), NetworkConstants.NETWORK_METHORD_GET, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testPostRequest() {
        RequestParams mPostRequestParams = new RequestParams();
        mPostRequestParams.getmParameters().add(new APIParameter("code", "4024"));

//        Map<String, String> params = new HashMap<String, String>();
//        params.put("code", "4024");

        RequestParams mPostHeaderParams = new RequestParams();
        mPostRequestParams.getmParameters().add(new APIParameter("Content-Type", "application/x-www-form-urlencoded"));

//        Map<String, String> headerParams = new HashMap<String, String>();
//        headerParams.put("Content-Type", "application/x-www-form-urlencoded");

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(Object networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse.toString());
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", networkResponse);
                }
            }, URL_POST, NetworkConstants.NETWORK_METHORD_POST, mPostRequestParams, mPostHeaderParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testPostPoliceExciseRequest() {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("data", "{\"plot_unique_id\":\"355617\",\"plot_physical_address\":\"owner\",\"plot_id\":\"355617\",\"location\":\"31.475885,74.3422574\",\"action_type\":\"1\",\"plot_sub_id\":\"1\",\"apk_version\":\"1.0\",\"imei_no\":\"862037029044152\",\"owners\":[{\"name\":\"Owner\",\"cnic\":\"11111-1111111-1\",\"number\":\"11111111111\",\"father_name\":\"Father\",\"address\":\"Permanent\"},{\"name\":\"Owner+1\",\"cnic\":\"22222-2222222-2\",\"number\":\"22222222222\",\"father_name\":\"Father+1\",\"address\":\"Permanent\"}],\"servants\":[{\"name\":\"Nam\",\"cnic\":\"33333-3333333-3\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"P\"},{\"name\":\"Nam\",\"cnic\":\"65656-5656565-6\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"Hdhdhd\"}],\"families\":[{\"name\":\"Raza\",\"cnic\":\"35201-9781615-5\",\"number\":\"00000000000\",\"family_relation\":\"Rel\"},{\"name\":\"Raza1\",\"cnic\":\"20000-0000000-0\",\"number\":\"02222222222\",\"family_relation\":\"R\"}],\"vehicles\":[{\"type\":\"Vzvzbdb\",\"reg_no\":\"Bdb-95-95959\",\"model_no\":\"Bddnnddn\"},{\"type\":\"Bsbsbsbssb\",\"reg_no\":\"Bsb-95-95959\",\"model_no\":\"Bsbsbsnsnsbs\"}],\"resident\":{\"name\":\"%D8%B3%DB%8C%D8%AF+%D8%B1%D8%B6%D8%A7+%D9%85%DB%81%D8%AF%DB%8C\",\"number\":\"22222222222\",\"cnic\":\"35201-9781615-5\",\"resident_agrement_from_date\":\"31-08-2016\",\"resident_agrement_to_date\":\"\",\"father_name\":\"%D8%B3%DB%8C%D8%AF+%D8%AC%D8%B9%D9%81%D8%B1+%D9%85%D8%B1%D8%AA%D8%B6%D9%B0%DB%8C\",\"floor_id\":\"7\",\"unit_id\":\"4\",\"address\":\"%E2%80%AE%D9%85%DA%A9%D8%A7%D9%86%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA6%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D8%B3%D9%B9%D8%B1%DB%8C%D9%B9%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA15%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D9%85%D8%AD%D9%84%DB%81%E2%80%AA+%E2%80%AC%D9%85%D8%BA%D9%84%D9%BE%D9%88%D8%B1%DB%81%D8%8C%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AA+%E2%80%AC%DA%A9%DB%8C%D9%86%D9%B9%D8%8C%E2%80%AA+%E2%80%AC%D8%B6%D9%84%D8%B9%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AC\",\"resident_type\":\"1\",\"is_closed\":\"no\"},\"imei\":\"862037029044152\"}");

        RequestParams mPostPoliceRequestParams = new RequestParams();
        mPostPoliceRequestParams.getmParameters().add(new APIParameter("data", "{\"plot_unique_id\":\"355617\",\"plot_physical_address\":\"owner\",\"plot_id\":\"355617\",\"location\":\"31.475885,74.3422574\",\"action_type\":\"1\",\"plot_sub_id\":\"1\",\"apk_version\":\"1.0\",\"imei_no\":\"862037029044152\",\"owners\":[{\"name\":\"Owner\",\"cnic\":\"11111-1111111-1\",\"number\":\"11111111111\",\"father_name\":\"Father\",\"address\":\"Permanent\"},{\"name\":\"Owner+1\",\"cnic\":\"22222-2222222-2\",\"number\":\"22222222222\",\"father_name\":\"Father+1\",\"address\":\"Permanent\"}],\"servants\":[{\"name\":\"Nam\",\"cnic\":\"33333-3333333-3\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"P\"},{\"name\":\"Nam\",\"cnic\":\"65656-5656565-6\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"Hdhdhd\"}],\"families\":[{\"name\":\"Raza\",\"cnic\":\"35201-9781615-5\",\"number\":\"00000000000\",\"family_relation\":\"Rel\"},{\"name\":\"Raza1\",\"cnic\":\"20000-0000000-0\",\"number\":\"02222222222\",\"family_relation\":\"R\"}],\"vehicles\":[{\"type\":\"Vzvzbdb\",\"reg_no\":\"Bdb-95-95959\",\"model_no\":\"Bddnnddn\"},{\"type\":\"Bsbsbsbssb\",\"reg_no\":\"Bsb-95-95959\",\"model_no\":\"Bsbsbsnsnsbs\"}],\"resident\":{\"name\":\"%D8%B3%DB%8C%D8%AF+%D8%B1%D8%B6%D8%A7+%D9%85%DB%81%D8%AF%DB%8C\",\"number\":\"22222222222\",\"cnic\":\"35201-9781615-5\",\"resident_agrement_from_date\":\"31-08-2016\",\"resident_agrement_to_date\":\"\",\"father_name\":\"%D8%B3%DB%8C%D8%AF+%D8%AC%D8%B9%D9%81%D8%B1+%D9%85%D8%B1%D8%AA%D8%B6%D9%B0%DB%8C\",\"floor_id\":\"7\",\"unit_id\":\"4\",\"address\":\"%E2%80%AE%D9%85%DA%A9%D8%A7%D9%86%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA6%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D8%B3%D9%B9%D8%B1%DB%8C%D9%B9%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA15%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D9%85%D8%AD%D9%84%DB%81%E2%80%AA+%E2%80%AC%D9%85%D8%BA%D9%84%D9%BE%D9%88%D8%B1%DB%81%D8%8C%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AA+%E2%80%AC%DA%A9%DB%8C%D9%86%D9%B9%D8%8C%E2%80%AA+%E2%80%AC%D8%B6%D9%84%D8%B9%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AC\",\"resident_type\":\"1\",\"is_closed\":\"no\"},\"imei\":\"862037029044152\"}"));

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(Object networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse.toString());
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", networkResponse);
                }
            }, URL_POLICE_EXCISE, NetworkConstants.NETWORK_METHORD_POST, mPostPoliceRequestParams, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
