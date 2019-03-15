package com.mobile.development.weatherapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.development.weatherapp.model.Weather;
import com.mobile.development.weatherapp.service.WeatherServiceImpl;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;

public class MainActivity extends AppCompatActivity {

    private WeatherServiceImpl weatherService;
    public static final String TAG = MainActivity.class.getSimpleName();

    // UI elements
    private TextView mTemperatureLabel;
    private TextView mTimeLabel;
    private TextView mLocationLabel;
    private ImageView mIconImage;
    private TextView mHumidityValue;
    private TextView mPrecipValue;
    private TextView mSummaryLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherService = new WeatherServiceImpl();
        mTemperatureLabel = findViewById( R.id.temperatureLabel );
        mTimeLabel = findViewById( R.id.timeLabel );
        mLocationLabel = findViewById( R.id.locationLabel );
        mIconImage = findViewById( R.id.iconImageView );
        mHumidityValue = findViewById( R.id.humidityValue );
        mPrecipValue = findViewById( R.id.precipValue );
        mSummaryLabel = findViewById( R.id.summaryLabel );

        new AsyncClassTask().execute();
    }

    private class AsyncClassTask extends AsyncTask< Void, Void, Weather >
    {

        private Weather retrievedWeather;

        @Override
        protected Weather doInBackground(Void... voids) {
            // Tallinn coordinates
            double latitude = 59.436962;
            double longitude = 24.753574;

            if( isNetworkAvailable() )
            {
                try
                {
                    retrievedWeather = weatherService.retrieveCurrentWeather( latitude, longitude );
                }
                catch ( IOException e )
                {
                    Log.v( TAG, MessageFormat.format( "Failed to retrieve weather for the provided location: {0}", e.getMessage() ) );
                    Toast.makeText( getApplicationContext(), "Could not retrieve weather, please restart the app in order to retry", Toast.LENGTH_SHORT ).show();
                }
            }
            else
            {
                Toast.makeText( getApplicationContext(), "Please enable a network connection", Toast.LENGTH_SHORT ).show();
            }

            return null;
        }

        private void initializeUI() {
            mTemperatureLabel.setText( Long.toString( Math.round( retrievedWeather.getTemperature() ) ) );
            mTimeLabel.setText( Instant.ofEpochMilli( retrievedWeather.getTime() ).atZone( ZoneId.systemDefault() ).toLocalDate().toString() );
            mSummaryLabel.setText( retrievedWeather.getSummary() );
            mPrecipValue.setText( Double.toString( retrievedWeather.getPrecipChance() ) );
            mHumidityValue.setText( Double.toString( retrievedWeather.getHumidity() ) );
            mIconImage.setImageResource( getResources().getIdentifier( retrievedWeather.getIcon(), "drawable", getPackageName() ) );
        }

        @Override
        protected void onPostExecute( Weather result )
        {
            initializeUI();
            super.onPostExecute( result );
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        boolean isAvailable = false;

        if ( manager != null )
        {
            networkInfo = manager.getActiveNetworkInfo();
        }
        if( networkInfo != null && networkInfo.isConnected() )
        {
            isAvailable = true;
        }
        return isAvailable;
    }
}
