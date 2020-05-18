package com.weather.service;

import com.weather.model.DayForecast;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ForecastWeather {
    private List<DayForecast> forecastWeatherList = new ArrayList<>();

    public List<DayForecast> getForecastWeatherList() {
        return forecastWeatherList;
    }

    public void setForecastWeather(String cityName) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            JSONReader jsonReader = new JSONReader(url);

            JSONObject jsonObject = new JSONObject(jsonReader.getJsonData());
            JSONArray jsonArray = jsonObject.getJSONArray("list");

            forecastWeatherList = Stream.of(0, 8, 16, 24, 32)
                    .map(dataNumber -> {
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
                        return new DayForecast(dayDate, new Image(linkImage), dayTemperature, nightTemperature);
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
