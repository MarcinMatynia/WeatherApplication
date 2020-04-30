package com.weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainWindow.fxml"));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Launcher.class.getResourceAsStream("view/images/icon.png")));
        stage.setTitle("Weather Application");

        stage.show();

    }
}
