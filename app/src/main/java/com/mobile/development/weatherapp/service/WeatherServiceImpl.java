package com.mobile.development.weatherapp.service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.development.weatherapp.constant.WeatherApiConstants;
import com.mobile.development.weatherapp.model.Weather;
import com.mobile.development.weatherapp.model.json.WeatherAdapter;
import com.mobile.development.weatherapp.util.HttpUtils;

import java.io.IOException;
import java.text.MessageFormat;

public class WeatherServiceImpl implements WeatherService {

    private static final String TAG = "WeatherServiceImpl";

    private final HttpUtils httpUtils = new HttpUtils();

    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping()
                                                      .setPrettyPrinting()
                                                      .registerTypeAdapter( Weather.class, new WeatherAdapter() )
                                                      .create();


    @Override
    public Weather retrieveCurrentWeather( double latitude, double longitude ) throws IOException
    {
        Log.v( TAG, MessageFormat.format( "Retrieving weather information for the following: longitude = {0}, latitude = {1}", longitude, latitude ) );

        String absoluteUrl = httpUtils.formatUrl( WeatherApiConstants.GET_WEATHER_WITH_PARAMS,
                                                  Double.toString( latitude ),
                                                  Double.toString( longitude ) );

        return GSON.fromJson( httpUtils.makeGetRequest( absoluteUrl ), Weather.class );
    }
}
