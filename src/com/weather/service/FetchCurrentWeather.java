package com.weather.service;

import com.weather.model.CurrentWeather;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.net.URL;

public class FetchCurrentWeather {
    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    private final CurrentWeather currentWeather = new CurrentWeather();

    public void fetchWeather(String cityName) {
        try {
            var url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            var jsonReader = new JSONReader(url);

            var jsonObject = new JSONObject(jsonReader.getJsonData());
            var jsonArray = jsonObject.getJSONArray("weather");

            //set image
            var icon = jsonArray.getJSONObject(0).getString("icon");
            var linkImage = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
            currentWeather.setImage(new Image(linkImage));

            //set temperature
            var temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp"));
            currentWeather.setTemperature(new Label(temp + " °C"));

            //set pressure
            var press = jsonObject.getJSONObject("main").getInt("pressure");
            currentWeather.setPressure(new Label(press + " hPa"));

            //set precipitation
            if (jsonObject.has("rain")) {
                var rain = jsonObject.getJSONObject("rain").getDouble("1h");
                if (!(rain > 0)) {
                    rain = jsonObject.getJSONObject("rain").getDouble("3h");
                }
                currentWeather.setPrecipitation(new Label(rain + " mm"));
            } else if (jsonObject.has("snow")) {
                var snow = jsonObject.getJSONObject("snow").getDouble("1h");
                if (!(snow > 0)) {
                    snow = jsonObject.getJSONObject("snow").getDouble("3h");
                }
                currentWeather.setPrecipitation(new Label(snow + " mm"));
            } else {
                currentWeather.setPrecipitation(new Label("0 mm"));
            }

            //set humidity
            var hum = jsonObject.getJSONObject("main").getInt("humidity");
            currentWeather.setHumidity(new Label(hum + " %"));

            //set wind
            var w = jsonObject.getJSONObject("wind").getDouble("speed");
            currentWeather.setWind(new Label(w + " m/s"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
