package com.mobile.development.weatherapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

/**
 * Utils class, which provides methods relevant to REST queries
 */
public class HttpUtils {

    public String makeGetRequest( String formattedUrl ) throws IOException
    {
        return doGetRequest( formattedUrl );
    }

    private String doGetRequest( String formattedUrl ) throws IOException
    {
        URL url = new URL( formattedUrl );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        String inputLine;
        BufferedReader in = new BufferedReader( new InputStreamReader( connection.getInputStream() ) );
        StringBuilder content = new StringBuilder();

        while( ( inputLine = in.readLine()) != null )
        {
            content.append( inputLine );
        }

        in.close();
        connection.disconnect();

        return content.toString();
    }

    public String formatUrl( String url, String... params )
    {
       return MessageFormat.format( url, params );
    }
}
