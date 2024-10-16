package org.example.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.MainApplication;

import java.io.IOException;

public final class SceneHelper {
    public static final String MAIN_SCENE = "main-view.fxml";
    public static final String GAME_SCENE = "game-view.fxml";
    public static final String USER_INPUT_SCENE = "username-view.fxml";


    private SceneHelper() {}

    public static void goToScreen(Node node, String scene) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(scene));
            Parent root = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error on navigation " + e.getMessage());
        }
    }
}
