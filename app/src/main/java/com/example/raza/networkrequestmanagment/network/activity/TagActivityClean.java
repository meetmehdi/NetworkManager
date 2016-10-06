package com.example.raza.networkrequestmanagment.network.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raza.networkrequestmanagment.NetworkRequestManagment;
import com.example.raza.networkrequestmanagment.R;
import com.example.raza.networkrequestmanagment.network.constants.NetworkConstants;
import com.example.raza.networkrequestmanagment.network.interfaces.NetworkManagerInterface;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TagActivityClean extends AppCompatActivity {

    private static final String TAG = AppCompatActivity.class.getSimpleName();

    public static final int REQUEST_CODE_PICTURE_TAKE_IMAGE_CAMERA = 20;

    public static String ipAddress = "sample.url";
    private static String baseURL = "sample.url";
    public static final String URL_CLEAN = "sample.url";
    public static String filePath;

    ImageView imageView;
    Button buttonSubmit;
    Button buttonLocation;
    TextView textViewName;
    TextView textViewEmis;
    TextView textViewLocation;

    private String name, emis;

    private byte[] pic1;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);
        initViews();
        initActivity();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    postData();
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pic1 == null) {
                    Intent intent;
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_PICTURE_TAKE_IMAGE_CAMERA);
                } else {
                    ImageUtils.getInstance().showDialogPicture(pic1, "Picture Taken");
                }
            }
        });
    }

    private void initViews() {
        imageView = (ImageView) findViewById(R.id.iv_picture);
        buttonSubmit = (Button) findViewById(R.id.btn_submit);
        buttonLocation = (Button) findViewById(R.id.btn_location);
        textViewName = (TextView) findViewById(R.id.tv_name);
        textViewEmis = (TextView) findViewById(R.id.tv_emis);
        textViewLocation = (TextView) findViewById(R.id.tv_emis);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initActivity() {
        name = "hardcorded";
        emis = "356316064133044";

        progressDialog = new ProgressDialog(getApplicationContext());

        textViewName.setText(name);
        textViewEmis.setText(emis);
    }


    private void resetFields(boolean shouldFinish) {
        try {
            File filebefore = new File(filePath);
            filebefore.delete();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        if (shouldFinish) {
            Intent intent = new Intent();
            intent.putExtra("emis", emis);
            setResult(100, intent);
            finish();
        } else
            finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_PICTURE_TAKE_IMAGE_CAMERA:
                try {
                    pic1 = ImageUtils.getInstance().setImageOnActivityResult(ImageUtils.TAKE_IMAGE_CAMERA, resultCode, data, imageView);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error loading image. Please delete some pictures and try again", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error loading image. Please try again", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isValid() {

        if (pic1 == null) {
            Toast.makeText(getApplicationContext(), "Take/attach picture", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void postData() {

        Map<String, String> params = new HashMap<>();
        params.put("categories", "Dump Site");
        params.put("zone", "");
        params.put("uc_no", "");
        params.put("town_name", "");
        params.put("place", "   ");
        params.put("gps_location", "23,23");
        params.put("imei_number", "864390020062790");
        params.put("description", "");
        params.put("in_or_out", "");

//      params.put("school_image", new DataPart(filePath, pic1, "image/jpeg"));
        Map<String, String> files = new HashMap<>();
        files.put("before_picture", filePath);
        files.put("after_picture", filePath);
        try {
            NetworkRequestManagment.mNetManager.networkMultipartRequest(new NetworkManagerInterface() {
                @Override
                public void onSuccess(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Success : " + networkResponse, Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(String networkResponse) {
                    Toast.makeText(getApplicationContext(), "Failure : " + networkResponse, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Failure : " + networkResponse);

                }
            }, URL_CLEAN, params, NetworkConstants.NETWORK_METHORD_POST, params, null, files, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
