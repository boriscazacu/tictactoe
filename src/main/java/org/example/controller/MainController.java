package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.helpers.AppCache;
import org.example.helpers.SceneHelper;

import static org.example.helpers.SceneHelper.GAME_SCENE;
import static org.example.helpers.SceneHelper.USER_INPUT_SCENE;

public class MainController {

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
    protected void startGameWithRandomChoice() {
        AppCache.getInstance().randomState();
        goToGameScreen();
    }

    @FXML
    protected void onGameStartButtonClick() {
        SceneHelper.goToScreen(gameBtn, USER_INPUT_SCENE);
    }

    private void goToGameScreen() {
        SceneHelper.goToScreen(gameBtn, GAME_SCENE);
    }
}
