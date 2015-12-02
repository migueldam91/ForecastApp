package com.example.miguel.forecastapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    String forecasturl;
    String TAG = "IOException: ";
    String serialKey = "bfcdb64232f785a039ba031a22f84d06", length = "40.5358", latitude = "%20-3.61661";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forecasturl = "https://api.forecast.io/forecast/" + serialKey + "/" + length + "," + latitude;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(forecasturl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }
            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.v(TAG,response.body().string());
                }

            }
        });


    }

}
