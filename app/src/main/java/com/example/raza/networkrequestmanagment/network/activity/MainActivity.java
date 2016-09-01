package com.example.raza.networkrequestmanagment.network.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.raza.networkrequestmanagment.NetworkRequestManagment;
import com.example.raza.networkrequestmanagment.R;
import com.example.raza.networkrequestmanagment.network.constants.NetworkConstants;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;
import com.example.raza.networkrequestmanagment.network.utils.NetworkUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private final String URL_GET_PARAMS = "http://www.gcepapers.com/services/temp.php";
    private final String URL_GET = "http://www.gcepapers.com/services/GetSubjectsList.php";
    private final String URL_POST = "http://gcepapers.com/services/GetSubjectData.php";
    private final String URL_POLICE_EXCISE= "http://www.tenantssurvey.punjab.gov.pk/api/napi/save_plot_data";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testPostPoliceExciseRequest();
//        testPostRequest();
//        testGetRequest();

        testGetRequestWithParams();
    }


    private void testGetRequestWithParams() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("param1", "a");
        params.put("param2", "b");
        params.put("param3", "c");
        params.put("param4", "d");
        params.put("param5", "e");

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "<Success>" + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse);
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", "<Failure>" + networkResponse);
                }
            }, NetworkUtils.getGetURL(URL_GET_PARAMS, params), NetworkConstants.NETWORK_METHORD_GET, null, params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testGetRequest() {
        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", "<Success>:" + networkResponse);
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", "<Failure>" + networkResponse);
                }
            }, URL_GET, NetworkConstants.NETWORK_METHORD_GET, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testPostRequest() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", "4024");

        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("Content-Type", "application/x-www-form-urlencoded");

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse);
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", networkResponse);
                }
            }, URL_POST, NetworkConstants.NETWORK_METHORD_POST, params, headerParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testPostPoliceExciseRequest() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("data","{\"plot_unique_id\":\"355617\",\"plot_physical_address\":\"owner\",\"plot_id\":\"355617\",\"location\":\"31.475885,74.3422574\",\"action_type\":\"1\",\"plot_sub_id\":\"1\",\"apk_version\":\"1.0\",\"imei_no\":\"862037029044152\",\"owners\":[{\"name\":\"Owner\",\"cnic\":\"11111-1111111-1\",\"number\":\"11111111111\",\"father_name\":\"Father\",\"address\":\"Permanent\"},{\"name\":\"Owner+1\",\"cnic\":\"22222-2222222-2\",\"number\":\"22222222222\",\"father_name\":\"Father+1\",\"address\":\"Permanent\"}],\"servants\":[{\"name\":\"Nam\",\"cnic\":\"33333-3333333-3\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"P\"},{\"name\":\"Nam\",\"cnic\":\"65656-5656565-6\",\"number\":\"\",\"father_name\":\"H\",\"address\":\"Hdhdhd\"}],\"families\":[{\"name\":\"Raza\",\"cnic\":\"35201-9781615-5\",\"number\":\"00000000000\",\"family_relation\":\"Rel\"},{\"name\":\"Raza1\",\"cnic\":\"20000-0000000-0\",\"number\":\"02222222222\",\"family_relation\":\"R\"}],\"vehicles\":[{\"type\":\"Vzvzbdb\",\"reg_no\":\"Bdb-95-95959\",\"model_no\":\"Bddnnddn\"},{\"type\":\"Bsbsbsbssb\",\"reg_no\":\"Bsb-95-95959\",\"model_no\":\"Bsbsbsnsnsbs\"}],\"resident\":{\"name\":\"%D8%B3%DB%8C%D8%AF+%D8%B1%D8%B6%D8%A7+%D9%85%DB%81%D8%AF%DB%8C\",\"number\":\"22222222222\",\"cnic\":\"35201-9781615-5\",\"resident_agrement_from_date\":\"31-08-2016\",\"resident_agrement_to_date\":\"\",\"father_name\":\"%D8%B3%DB%8C%D8%AF+%D8%AC%D8%B9%D9%81%D8%B1+%D9%85%D8%B1%D8%AA%D8%B6%D9%B0%DB%8C\",\"floor_id\":\"7\",\"unit_id\":\"4\",\"address\":\"%E2%80%AE%D9%85%DA%A9%D8%A7%D9%86%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA6%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D8%B3%D9%B9%D8%B1%DB%8C%D9%B9%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA15%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D9%85%D8%AD%D9%84%DB%81%E2%80%AA+%E2%80%AC%D9%85%D8%BA%D9%84%D9%BE%D9%88%D8%B1%DB%81%D8%8C%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AA+%E2%80%AC%DA%A9%DB%8C%D9%86%D9%B9%D8%8C%E2%80%AA+%E2%80%AC%D8%B6%D9%84%D8%B9%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AC\",\"resident_type\":\"1\",\"is_closed\":\"no\"},\"imei\":\"862037029044152\"}");
//        params.put("data", "{\n" +
//                "  \"plot_unique_id\": \"355618\",\n" +
//                "  \"plot_physical_address\": \"b\",\n" +
//                "  \"plot_id\": \"355618\",\n" +
//                "  \"location\": \"31.4758171,74.3425398\",\n" +
//                "  \"action_type\": \"1\",\n" +
//                "  \"plot_sub_id\": \"6\",\n" +
//                "  \"apk_version\": \"1.0\",\n" +
//                "  \"imei_no\": \"862037029044152\",\n" +
//                "  \"owners\": [\n" +
//                "    \n" +
//                "  ],\n" +
//                "  \"servants\": [\n" +
//                "    \n" +
//                "  ],\n" +
//                "  \"families\": [\n" +
//                "    \n" +
//                "  ],\n" +
//                "  \"vehicles\": [\n" +
//                "    \n" +
//                "  ],\n" +
//                "  \"resident\": {\n" +
//                "    \"name\": \"??? ??? ????\",\n" +
//                "    \"number\": \"99999999999\",\n" +
//                "    \"cnic\": \"35201-9781615-5\",\n" +
//                "    \"resident_agrement_from_date\": \"26-08-2016\",\n" +
//                "    \"resident_agrement_to_date\": \"26-08-2016\",\n" +
//                "    \"father_name\": \"org.apache.http.entity.mime.content.StringBody@c98facb\",\n" +
//                "    \"floor_id\": \"4\",\n" +
//                "    \"unit_id\": \"4\",\n" +
//                "    \"address\": \"%E2%80%AE%D9%85%DA%A9%D8%A7%D9%86%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA6%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D8%B3%D9%B9%D8%B1%DB%8C%D9%B9%E2%80%AA+%E2%80%AC%D9%86%D9%85%D8%A8%D8%B1%E2%80%AA15%E2%80%AC%D8%8C%E2%80%AA+%E2%80%AC%D9%85%D8%AD%D9%84%DB%81%E2%80%AA+%E2%80%AC%D9%85%D8%BA%D9%84%D9%BE%D9%88%D8%B1%DB%81%D8%8C%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AA+%E2%80%AC%DA%A9%DB%8C%D9%86%D9%B9%D8%8C%E2%80%AA+%E2%80%AC%D8%B6%D9%84%D8%B9%E2%80%AA+%E2%80%AC%D9%84%D8%A7%DB%81%D9%88%D8%B1%E2%80%AC\",\n" +
//                "    \"resident_type\": \"0\",\n" +
//                "    \"is_closed\": \"no\"\n" +
//                "  },\n" +
//                "  \"imei\": \"862037029044152\"\n" +
//                "}");

        Map<String, String> headerParams = new HashMap<String, String>();

        try {
            NetworkRequestManagment.mNetManager.networkSubmitRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Success>", networkResponse);
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.i(TAG + "<Failure>", networkResponse);
                }
            }, URL_POLICE_EXCISE, NetworkConstants.NETWORK_METHORD_POST, params, headerParams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
