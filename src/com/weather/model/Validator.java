package com.weather.model;

import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private final String cityName;

    public Validator(String cityName) {
        this.cityName = cityName;
    }

    public boolean ifValidate(){
        if(ifRegex()){
            try{
                URL weather = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
                URL forecast = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");

                HttpsURLConnection weatherURLConnection = (HttpsURLConnection) weather.openConnection();
                HttpsURLConnection forecastURLConnection = (HttpsURLConnection) forecast.openConnection();

                return weatherURLConnection.getResponseCode() == 200 && forecastURLConnection.getResponseCode() == 200;

            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean ifRegex(){
        String regex = "^[a-zA-Z\\-ąćęłńóśźżĄĆĘŁŃÓŚŹŻ\\x20]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cityName);
        return matcher.matches();
    }
}
