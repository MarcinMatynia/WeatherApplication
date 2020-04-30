package com.weather.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;


public class CurrentWeather {
    private Image image;
    private Label temperature;
    private Label pressure;
    private Label precipitation;
    private Label humidity;
    private Label wind;
    private final String cityName;

    public CurrentWeather(String cityName) {
        this.cityName = cityName;
        setWeather();
    }

    private void setWeather(){
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&appid=3912ed1a6ff815dbd2cd0b6b7643707e");
            JSONReader jsonReader = new JSONReader(url);

            JSONObject jsonObject = new JSONObject(jsonReader.getJsonData());
            JSONArray jsonArray = jsonObject.getJSONArray("weather");

            //set image
            String icon = jsonArray.getJSONObject(0).getString("icon");
            String linkImage = "http://openweathermap.org/img/wn/" + icon + "@2x.png";
            image = new Image(linkImage);

            //set temperature
            int temp = (int) Math.round(jsonObject.getJSONObject("main").getDouble("temp"));
            temperature = new Label(temp + " °C");

            //set pressure
            int press = jsonObject.getJSONObject("main").getInt("pressure");
            pressure = new Label(press + " hPa");

            //set precipitation
            if(jsonObject.has("rain")){
                double rain = jsonObject.getJSONObject("rain").getDouble("1h");
                if (!(rain > 0)) {
                    rain = jsonObject.getJSONObject("rain").getDouble("3h");
                }
                precipitation = new Label(rain + " mm");
            } else if (jsonObject.has("snow")){
                double snow = jsonObject.getJSONObject("snow").getDouble("1h");
                if(!(snow>0)){
                    snow = jsonObject.getJSONObject("snow").getDouble("3h");
                }
                precipitation = new Label(snow + " mm");
            } else {
                precipitation = new Label("0 mm");
            }

            //set humidity
            int hum = jsonObject.getJSONObject("main").getInt("humidity");
            humidity = new Label(hum + " %");

            //set wind
            double w = jsonObject.getJSONObject("wind").getDouble("speed");
            wind = new Label(w + " m/s");

        } catch (Exception e){
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
