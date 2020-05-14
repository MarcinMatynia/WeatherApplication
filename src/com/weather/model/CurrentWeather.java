package com.weather.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.json.JSONObject;

import java.net.URL;

public class CurrentWeather {
    private Image image;
    private Label temperature;
    private Label pressure;
    private Label precipitation;
    private Label humidity;
    private Label wind;

    public void fetchWeather(String cityName) {
        try {
            var url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            var jsonReader = new JSONReader(url);

            var jsonObject = new JSONObject(jsonReader.getJsonData());
            var jsonArray = jsonObject.getJSONArray("weather");

            //set image
            var icon = jsonArray.getJSONObject(0).getString("icon");
            var linkImage = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
            image = new Image(linkImage);

            //set temperature
            var temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp"));
            temperature = new Label(temp + " °C");

            //set pressure
            var press = jsonObject.getJSONObject("main").getInt("pressure");
            pressure = new Label(press + " hPa");

            //set precipitation
            if (jsonObject.has("rain")) {
                var rain = jsonObject.getJSONObject("rain").getDouble("1h");
                if (!(rain > 0)) {
                    rain = jsonObject.getJSONObject("rain").getDouble("3h");
                }
                precipitation = new Label(rain + " mm");
            } else if (jsonObject.has("snow")) {
                var snow = jsonObject.getJSONObject("snow").getDouble("1h");
                if (!(snow > 0)) {
                    snow = jsonObject.getJSONObject("snow").getDouble("3h");
                }
                precipitation = new Label(snow + " mm");
            } else {
                precipitation = new Label("0 mm");
            }

            //set humidity
            var hum = jsonObject.getJSONObject("main").getInt("humidity");
            humidity = new Label(hum + " %");

            //set wind
            var w = jsonObject.getJSONObject("wind").getDouble("speed");
            wind = new Label(w + " m/s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public Label getTemperature() {
        return temperature;
    }

    public Label getPressure() {
        return pressure;
    }

    public Label getPrecipitation() {
        return precipitation;
    }

    public Label getHumidity() {
        return humidity;
    }

    public Label getWind() {
        return wind;
    }
}
