package com.mobile.development.weatherapp.model.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mobile.development.weatherapp.model.Weather;

import java.lang.reflect.Type;

public class WeatherAdapter implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) throws JsonParseException {

        JsonObject weatherInfo = json.getAsJsonObject();
        JsonObject specificWeatherInfo = weatherInfo.getAsJsonObject( "currently" );

        return new Weather( getTemperature( specificWeatherInfo ), getHumidity( specificWeatherInfo ), getPrecipChance( specificWeatherInfo ),
                                getTime( specificWeatherInfo ), getTimezone( weatherInfo ), getIcon( specificWeatherInfo ), getSummary( specificWeatherInfo ) );
    }

    private String getSummary(JsonObject weatherInfo) {
        return weatherInfo.get( "summary" ).getAsString();
    }

    private String getIcon(JsonObject weatherInfo) {
        return weatherInfo.get( "icon" ).getAsString();
    }

    private String getTimezone(JsonObject weatherInfo) {
        return weatherInfo.get( "timezone" ).getAsString();
    }

    private long getTime(JsonObject weatherInfo) {
        return weatherInfo.get( "time" ).getAsLong();
    }

    private double getPrecipChance(JsonObject weatherInfo) {
        return weatherInfo.get( "precipProbability" ).getAsDouble();
    }

    private double getHumidity(JsonObject weatherInfo) {
        return weatherInfo.get( "humidity" ).getAsDouble();
    }

    private double getTemperature(JsonObject weatherInfo) {
        return weatherInfo.get( "temperature" ).getAsDouble();
    }
}
