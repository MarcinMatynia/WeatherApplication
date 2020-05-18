package com.weather.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class CurrentWeather {
    private Image image;
    private Label temperature;
    private Label pressure;
    private Label precipitation;
    private Label humidity;
    private Label wind;

    public CurrentWeather() {
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

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTemperature(Label temperature) {
        this.temperature = temperature;
    }

    public void setPressure(Label pressure) {
        this.pressure = pressure;
    }

    public void setPrecipitation(Label precipitation) {
        this.precipitation = precipitation;
    }

    public void setHumidity(Label humidity) {
        this.humidity = humidity;
    }

    public void setWind(Label wind) {
        this.wind = wind;
    }
}
