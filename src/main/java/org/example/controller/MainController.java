package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.example.MainApplication;
import org.example.interfaces.Game;
import org.example.interfaces.Navigation;

public class MainController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onStartButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onGameStartButtonClick() {
        Navigation navigation = MainApplication.getNavigation();
        System.out.println(navigation);
        navigation.onLocationForward();
    }
}
