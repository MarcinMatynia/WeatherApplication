package com.weather.model;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class DayForecast {
    private final Label date;
    private final Image image;
    private final String dayTemperature;
    private final String nightTemperature;

    public DayForecast(Label date, Image image, String dayTemperature, String nightTemperature) {
        this.date = date;
        this.image = image;
        this.dayTemperature = dayTemperature;
        this.nightTemperature = nightTemperature;
    }

    public Label getDate() {
        return date;
    }

    public Image getImage() {
        return image;
    }

    public String getDayTemperature() {
        return dayTemperature;
    }

    public String getNightTemperature() {
        return nightTemperature;
    }
}
