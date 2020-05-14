package com.weather.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONReader {
    private final URL url;

    public JSONReader(URL url) {
        this.url = url;
    }

    public String getJsonData(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            return stringBuilder.toString();
        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
