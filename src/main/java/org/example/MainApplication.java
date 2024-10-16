package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        setScene();
        stage.setWidth(590);
        stage.setHeight(450);
        stage.show();
    }

    private void setScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(
                org.example.helpers.SceneHelper.MAIN_SCENE
        ));
        Scene scene = new Scene(fxmlLoader.load(), 500, 450);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}