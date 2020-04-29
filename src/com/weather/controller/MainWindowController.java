package com.weather.controller;

import com.weather.model.CurrentWeather;
import com.weather.model.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MainWindowController {

    @FXML
    private TextField leftTextField;

    @FXML
    private Button leftButton;

    @FXML
    private ListView<?> leftListView;

    @FXML
    private GridPane leftFGridPane;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField rightTextField;

    @FXML
    private Button rightButton;

    @FXML
    private GridPane rightGridPane;

    @FXML
    private ListView<?> rightListView;

    private String cityName;
    private CurrentWeather currentWeather;
    //private ForecastWeather forecastWeather;

    @FXML
    void getWeatherForecast(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        String buttonName = sourceButton.getId();

        switch (buttonName){
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

        Validator validator = new Validator(cityName);
        if(validator.ifValidate()){
            errorLabel.setVisible(false);
            currentWeather = new CurrentWeather(cityName);
            //forecastWeather = new ForecastWeather(cityName);
            //forecastWeather.setWeather(cityName);
            if(buttonName.equals("leftButton")){
                displayCurrentWeather(leftFGridPane);
                //seeForecastWeather(leftFGridPane);
            } else {
                displayCurrentWeather(rightGridPane);
               //seeForecastWeather(rightGridPane);
            }
        } else {
            errorLabel.setText("Sorry, you entered an invalid city name! Remember that you can only use letters and Polish characters!");
            errorLabel.setVisible(true);
        }
    }

    private void displayForecastWeather(GridPane gridPane) {
    }

    private void displayCurrentWeather(GridPane gridPane) {
        //clean gridPane
        gridPane.getChildren().clear();
        //image
        Image image = currentWeather.getImage();

        //temp
        Label temp = currentWeather.getTemperature();
        GridPane.setConstraints(temp,1,0);

        //pressure
        Label pressure = currentWeather.getPressure();
        GridPane.setConstraints(pressure,0,1);

        //precipitation
        Label precipitation = currentWeather.getPrecipitation();
        GridPane.setConstraints(precipitation,1,1);

        //humidity
        Label humidity = currentWeather.getHumidity();
        GridPane.setConstraints(humidity,0,2);

        //wind
        Label wind = currentWeather.getWind();
        GridPane.setConstraints(wind,1,2);

        gridPane.getChildren().addAll(new ImageView(image), temp, pressure, precipitation, humidity, wind);
    }

}
