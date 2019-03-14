package com.mobile.development.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Weather currentWeather;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = BuildConfig.ApiKey;

        // Tallinn coordinates
        double latitude = 59.436962;
        double logitude = 24.753574;

        String forecastUrl ="https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + logitude;
        currentWeather = new Weather();

        if(isNetworkAvaliable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(forecastUrl)
                    .build();
            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        String json = response.body() != null ? response.body().string() : null;
                        Log.v(TAG, json);

                        if(response.isSuccessful()) {
                            JSONObject forecast = new JSONObject(json);
                            JSONObject currently = forecast.getJSONObject("currently");

                            //TODO Parse json on success response
                        } else {
                            //TODO notify user about response error
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught",e);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private boolean isNetworkAvaliable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        boolean isAvaliable = false;

        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }
        if(networkInfo != null && networkInfo.isConnected()){
            isAvaliable = true;
        }
        return isAvaliable;
    }
}
