package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.interfaces.Navigation;

import java.io.IOException;

public class MainApplication extends Application implements Navigation {

    public static final String MAIN_SCENE = "main-view.fxml";
    public static final String GAME_SCENE = "game-view.fxml";
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("TicTacToe");
        stage.setResizable(false);
        setScene(MAIN_SCENE);
        stage.setWidth(590);
        stage.setHeight(450);
        stage.show();
    }

    private void setScene(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resource));
        Scene scene = new Scene(fxmlLoader.load(), 500, 450);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void onLocationForward() {
        try {
            setScene(GAME_SCENE);
        } catch (IOException e) {
            System.out.println("Some error on GAME_SCENE");
        }
    }

    @Override
    public void onLocationBack() {
        try {
            setScene(MAIN_SCENE);
        } catch (IOException e) {
            System.out.println("Some error on MAIN_SCENE");
        }
    }
}