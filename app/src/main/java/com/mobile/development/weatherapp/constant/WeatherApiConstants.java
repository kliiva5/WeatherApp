package com.mobile.development.weatherapp.constant;

import com.mobile.development.weatherapp.BuildConfig;

public class WeatherApiConstants {

    public static final String GET_WEATHER_WITH_PARAMS = "https://api.darksky.net/forecast/" + BuildConfig.ApiKey + "/{0},{1}";
}
