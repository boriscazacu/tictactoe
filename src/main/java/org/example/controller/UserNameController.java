package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.helpers.AppCache;
import org.example.helpers.SceneHelper;

import static org.example.helpers.SceneHelper.GAME_SCENE;

public class UserNameController {

    @FXML
    private TextField username;

    @FXML
    private TextField oUsername;

    @FXML
    public void saveAndStartGame() {
        AppCache.getInstance().userToUserInit(
            username.getText(),
            oUsername.getText()
        );
        SceneHelper.goToScreen(username, GAME_SCENE);
    }
}
