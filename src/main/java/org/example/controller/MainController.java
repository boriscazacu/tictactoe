package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.MainApplication;
import org.example.helpers.AppCache;

import java.io.IOException;

public class MainController {
    public static final String GAME_SCENE = "game-view.fxml";

    @FXML
    private Button gameBtn;

    @FXML
    protected void onXButtonClick() {
        AppCache.getInstance().setXState();
        goToGameScreen();
    }

    @FXML
    protected void onOButtonClick() {
        AppCache.getInstance().setOState();
        goToGameScreen();
    }

    @FXML
    protected void onGameStartButtonClick() {
        AppCache.getInstance().randomState();
        goToGameScreen();
    }

    private void goToGameScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(GAME_SCENE));
            Parent root = loader.load();

            Stage stage = (Stage) gameBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error on navigation " + e.getMessage());
        }
    }
}
