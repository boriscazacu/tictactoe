package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.example.helpers.AppCache;
import org.example.helpers.Database;
import org.example.helpers.SceneHelper;

import java.util.Map;

import static org.example.helpers.SceneHelper.GAME_SCENE;
import static org.example.helpers.SceneHelper.MAIN_SCENE;

public class UserNameController {

    @FXML
    private TextField username;

    @FXML
    private TextField oUsername;

    @FXML
    private CheckBox keepBox;

    private final Database database = new Database();

    @FXML
    public void initialize() {
        database.create();

        Map<Integer, String> names = database.getNames();
        if (!names.isEmpty()) {
            username.setText(names.get(1));
            oUsername.setText(names.get(2));
        }
    }

    @FXML
    public void saveAndStartGame() {
        AppCache.getInstance().userToUserInit(
            username.getText(),
            oUsername.getText()
        );
        if (keepBox.isSelected()) {
            database.insert(
                username.getText(),
                oUsername.getText()
            );
        }
        SceneHelper.goToScreen(username, GAME_SCENE);
    }

    @FXML
    public void goBackToHomeScreen() {
        SceneHelper.goToScreen(username, MAIN_SCENE);
    }
}
