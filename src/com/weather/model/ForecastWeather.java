package com.weather.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForecastWeather {
    private final String cityName;
    private final ArrayList<DayForecast> forecastWeatherList;

    public ForecastWeather(String cityName) {
        this.cityName = cityName;
        forecastWeatherList = new ArrayList<>();
        setForecastWeather();
    }

    public ArrayList<DayForecast> getForecastWeatherList() {
        return forecastWeatherList;
    }

    public void setForecastWeather() {
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            JSONReader jsonReader = new JSONReader(url);

            JSONObject jsonObject = new JSONObject(jsonReader.getJsonData());
            JSONArray jsonArray = jsonObject.getJSONArray("list");

            int dataNumber = 0;
            while(dataNumber <=35){
                //set date
                long timestamp = jsonArray.getJSONObject(dataNumber).getInt("dt");
                String date = new SimpleDateFormat("EEE dd/MM").format(new Date(timestamp * 1000L));
                Label dayDate = new Label(date);

                //set image
                String icon = jsonArray.getJSONObject(dataNumber).getJSONArray("weather").getJSONObject(0).getString("icon");
                String linkImage = "http://openweathermap.org/img/wn/" + icon + "@2x.png";

                //set day temperature
                String dayTemperature = (int) Math.round(jsonArray.getJSONObject(dataNumber).getJSONObject("main").getDouble("temp")) + " °C";

                //set night temperature
                String nightTemperature = (int) Math.round(jsonArray.getJSONObject(dataNumber + 4).getJSONObject("main").getDouble("temp")) + " °C";

                //create new day forecast
                DayForecast dayForecast = new DayForecast(dayDate, new Image(linkImage), dayTemperature, nightTemperature);

                //add day forecast to array
                forecastWeatherList.add(dayForecast);

                dataNumber = dataNumber + 8;

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
