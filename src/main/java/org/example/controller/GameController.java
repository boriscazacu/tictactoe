package org.example.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.example.board.Board;
import org.example.helpers.AppCache;
import org.example.interfaces.Player;
import org.example.player.User;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {

    @FXML
    private Label firstPlayerScore;

    @FXML
    private Label secondPlayerScore;

    @FXML
    private Label field00;

    @FXML
    private Label field01;

    @FXML
    private Label field02;

    @FXML
    private Label field10;

    @FXML
    private Label field11;

    @FXML
    private Label field12;

    @FXML
    private Label field20;

    @FXML
    private Label field21;

    @FXML
    private Label field22;

    @FXML
    private Pane gameOverPane;

    @FXML
    private Label paneLabel;

    @FXML
    private Label userName;

    @FXML
    private TextField currentMove;

    @FXML
    private Label botName;

    @FXML
    private Button exitBtn;

    @FXML
    private Button playAgainBtn;

    private final Board board = new Board(3);

    @FXML
    public void initialize() {
        userName.setText(String.format("%s Score:", AppCache.getInstance().getFirstPlayer().name()));
        botName.setText(String.format("%s Score:", AppCache.getInstance().getSecondPlayer().name()));
        setCurrentMove();

        // Set listeners
        field00.setOnMouseClicked(c -> move(field00,0,0));
        field01.setOnMouseClicked(c -> move(field01,0,1));
        field02.setOnMouseClicked(c -> move(field02,0,2));
        field10.setOnMouseClicked(c -> move(field10,1,0));
        field11.setOnMouseClicked(c -> move(field11,1,1));
        field12.setOnMouseClicked(c -> move(field12,1,2));
        field20.setOnMouseClicked(c -> move(field20,2,0));
        field21.setOnMouseClicked(c -> move(field21,2,1));
        field22.setOnMouseClicked(c -> move(field22,2,2));
        exitBtn.setOnMouseClicked(m -> System.exit(999));
        playAgainBtn.setOnMouseClicked(m -> playNewGame());
    }

    private void move(Label label, int x, int y) {
        Player player = AppCache.getInstance().getPlayerToMove();
        if (player instanceof User) {
            label.setText(
                player.move(board, x, y)
            );
        }
        setCurrentMove();
        this.checkGameStatus(player);

        if (AppCache.getInstance().isPlayingWithBot()) {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            // Schedule the task to run after 5 seconds
            scheduler.schedule(this::botMove, 1, TimeUnit.SECONDS);

        }
    }

    private void botMove() {
        Player bot = AppCache.getInstance().getPlayerToMove();

        String move = bot.move(board, 0, 0);
        Class<?> personClass = this.getClass();
        try {
            Field nameField = personClass.getDeclaredField("field" + move);
            nameField.setAccessible(true);
            Label botLabel = (Label) nameField.get(this);
            Platform.runLater(() -> botLabel.setText(bot.getElement().value()));
            nameField.setAccessible(false);
            setCurrentMove();
            this.checkGameStatus(bot);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCurrentMove() {
        currentMove.setText(String.format("%s Move", AppCache.getInstance().getNextMove().name()));
    }

    private void playNewGame() {
        field00.setText("");
        field01.setText("");
        field02.setText("");
        field10.setText("");
        field11.setText("");
        field12.setText("");
        field20.setText("");
        field21.setText("");
        field22.setText("");
        board.clean();
        gameOverPane.setVisible(false);
        gameOverPane.setDisable(true);
    }

    private void checkGameStatus(Player player) {
        if (board.isFull()) {
            paneLabel.setText("Board is full");
            gameOverPane.setVisible(true);
            gameOverPane.setDisable(false);
            return;
        }
        if (player.winGame(board)) {
            String text;
            if (player instanceof User) {
                text = String.format("%s win, Congrats !!:)", player.name());
                player.increment();
                firstPlayerScore.setText(String.valueOf(AppCache.getInstance().getFirstPlayer().getScore()));
                secondPlayerScore.setText(String.valueOf(AppCache.getInstance().getSecondPlayer().getScore()));
            } else {
                text = "Bot win, try again :(";
                secondPlayerScore.setText(String.valueOf(player.increment().getScore()));
            }
            paneLabel.setText(text);
            gameOverPane.setVisible(true);
            gameOverPane.setDisable(false);
        }
    }
}
