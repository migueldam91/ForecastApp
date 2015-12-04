package com.example.miguel.forecastapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {
    String forecasturl;
    String TAG = "TAG: ";
    String serialKey = "bfcdb64232f785a039ba031a22f84d06", length = "40.5358", latitude = "%20-3.61661";
    private Current current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forecasturl = "https://api.forecast.io/forecast/" + serialKey + "/" + length + "," + latitude;

        if (isNetworkAvailable()) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(forecasturl).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) {
                    /**
                     * jsonData = datos API
                     */try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (!response.isSuccessful()) {
                            alertUserAboutError();
                        } else {

                            current = getCurrentDetails(jsonData);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString());
                    } catch (IOException e) {
                        Log.e(TAG, e.toString());
                    }

                }
            });
        } else {
            Log.e(TAG, "No Connection");
        }

    }

    /**
     * Método para obtener detalles de interés
     *
     * @param jsonData cadena de texto con resultado API
     * @return Objeto Current
     */
    public Current getCurrentDetails(String jsonData) throws JSONException {
        //creación de objeto JSONObject para almacenar el objeto raíz
        JSONObject forecast = new JSONObject(jsonData);
        //Almacenamos en timezone el string que hay en la clave timezone en JSON
        String timezone = forecast.getString("timezone");
        //creación del objeto JSON que almacene el objeto currently definido <b>dentro</b> de la raíz.
        JSONObject currently = forecast.getJSONObject("currently");
        //Creación de un objeto Current donde vayamos almacendo uno por uno los valores que nos interesan
        Current currentWeather = new Current();
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTimeZone(timezone);
        Log.i("Obteniendo desde JSON: ", currentWeather.getFormattedTime());
        return currentWeather;
    }



    /**
     * La clase permite comprobar si hay conexión a red.
     *
     * @return isAvailable true si hay red
     */
    private boolean isNetworkAvailable() {
        //Gestor de conectividad
        ConnectivityManager manager;
        manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        //Objeto que recupera el estado
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Error dialog:");

    }

}
