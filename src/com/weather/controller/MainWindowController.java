package com.weather.controller;

import com.weather.service.FetchCurrentWeather;
import com.weather.service.ForecastWeather;
import com.weather.service.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainWindowController {

    @FXML
    private TextField leftTextField;

    @FXML
    private GridPane leftCurrentWeatherGridPane;

    @FXML
    private GridPane leftForecastGridPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField rightTextField;

    @FXML
    private GridPane rightCurrentWeatherGridPane;

    @FXML
    private GridPane rightForecastGridPane;

    private String cityName;
    private final ForecastWeather forecastWeather = new ForecastWeather();
    private final Validator validator = new Validator();
    private final FetchCurrentWeather fetchCurrentWeather = new FetchCurrentWeather();

    @FXML
    void getWeatherForecast(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String buttonName = sourceButton.getId();

        switch (buttonName) {
            case "leftButton":
                cityName = leftTextField.getText();
                break;
            case "rightButton":
                cityName = rightTextField.getText();
                break;
            default:
                errorLabel.setText("Unexpected error!");
                errorLabel.setVisible(true);
                break;
        }

        if (validator.validate(cityName)) {
            errorLabel.setVisible(false);
            fetchCurrentWeather.fetchWeather(cityName);
            forecastWeather.setForecastWeather(cityName);
            if (buttonName.equals("leftButton")) {
                displayCurrentWeather(leftCurrentWeatherGridPane);
                displayForecastWeather(leftForecastGridPane);
            } else {
                displayCurrentWeather(rightCurrentWeatherGridPane);
                displayForecastWeather(rightForecastGridPane);
            }
        } else {
            errorLabel.setText("Sorry, you entered an invalid city name! Remember that you can only use letters and Polish characters!");
            errorLabel.setVisible(true);
        }
    }

    private void displayForecastWeather(GridPane gridPane) {
        var forecasts = forecastWeather.getForecastWeatherList();

        //clean gridPane
        gridPane.getChildren().clear();

        for (int i = 0; i < forecasts.size(); i++) {

            //set date
            Label date = forecasts.get(i).getDate();
            GridPane.setConstraints(date, 0, i);

            //image
            ImageView image = new ImageView(forecasts.get(i).getImage());
            GridPane.setConstraints(image, 1, i);

            //set text
            Label text = new Label(forecasts.get(i).getDayTemperature() + " / " + forecasts.get(i).getNightTemperature());
            GridPane.setConstraints(text, 2, i);

            gridPane.getChildren().addAll(date, image, text);

        }
        gridPane.setVisible(true);
    }

    private void displayCurrentWeather(GridPane gridPane) {
        var currentWeather = fetchCurrentWeather.getCurrentWeather();
        //clean gridPane
        gridPane.getChildren().clear();
        //image
        Image image = currentWeather.getImage();

        //temp
        Label temp = currentWeather.getTemperature();
        GridPane.setConstraints(temp, 1, 0);

        //pressure
        Label pressure = currentWeather.getPressure();
        GridPane.setConstraints(pressure, 0, 1);

        //precipitation
        Label precipitation = currentWeather.getPrecipitation();
        GridPane.setConstraints(precipitation, 1, 1);

        //humidity
        Label humidity = currentWeather.getHumidity();
        GridPane.setConstraints(humidity, 0, 2);

        //wind
        Label wind = currentWeather.getWind();
        GridPane.setConstraints(wind, 1, 2);


        gridPane.getChildren().addAll(new ImageView(image), temp, pressure, precipitation, humidity, wind);
        gridPane.setVisible(true);
    }

}
