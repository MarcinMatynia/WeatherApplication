package com.weather.model;

import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String regex = "^[a-zA-Z\\-ąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\x20]+$";
    private final Pattern pattern = Pattern.compile(regex);

    public boolean validate(String cityName) {
        if (cityNameMatches(cityName)) {
            URL weather = createUrl("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            URL forecast = createUrl("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            return validateUrl(weather) && validateUrl(forecast);
        }
        return false;
    }

    private URL createUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Somenthing went wrong!");
        }
    }

    private boolean validateUrl(URL url) {
        try {
            HttpsURLConnection weatherURLConnection = (HttpsURLConnection) url.openConnection();
            return weatherURLConnection.getResponseCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean cityNameMatches(String cityName) {
        Matcher matcher = pattern.matcher(cityName);
        return matcher.matches();
    }

}
