package com.mobile.development.weatherapp.service;

import com.mobile.development.weatherapp.model.Weather;

import java.io.IOException;

public interface WeatherService {

    Weather retrieveCurrentWeather( double latitude, double longitude ) throws IOException;
}
