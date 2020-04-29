module WeatherApplication {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires org.json;

    opens com.weather;
    opens com.weather.controller;
    opens com.weather.view;
    opens com.weather.model;
}