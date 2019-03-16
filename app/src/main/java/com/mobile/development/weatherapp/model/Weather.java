package com.mobile.development.weatherapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Weather {
    private final double temperature;
    private final double humidity;
    private final double precipChance;
    private final long time;
    private final String timezone;
    private final String icon;
    private final String summary;
}
