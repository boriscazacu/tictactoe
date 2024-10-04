package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.board.Board;
import org.example.board.OElement;
import org.example.board.XElement;
import org.example.enums.Errors;
import org.example.helpers.Printer;
import org.example.interfaces.Player;
import org.example.player.Bot;
import org.example.player.User;

import java.lang.reflect.Field;

public class GameController {

    @FXML
    private Label playerScore;

    @FXML
    private Label botScore;

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
    private Button exitBtn;

    @FXML
    private Button playAgainBtn;

    private final Board board = new Board(3);
    private User user;
    private Bot bot;

    @FXML
    public void initialize() {
        System.out.println("init");
        selectElement();
        field00.setOnMouseClicked(c -> field00.setText(user.move(0,0)));
        field01.setOnMouseClicked(c -> field01.setText(user.move(0,1)));
        field02.setOnMouseClicked(c -> field02.setText(user.move(0,2)));
        field10.setOnMouseClicked(c -> field10.setText(user.move(1,0)));
        field11.setOnMouseClicked(c -> field11.setText(user.move(1,1)));
        field12.setOnMouseClicked(c -> field12.setText(user.move(1,2)));
        field20.setOnMouseClicked(c -> field20.setText(user.move(2,0)));
        field21.setOnMouseClicked(c -> field21.setText(user.move(2,1)));
        field22.setOnMouseClicked(c -> field22.setText(user.move(2,2)));
        exitBtn.setOnMouseClicked(m -> System.exit(999));
        playAgainBtn.setOnMouseClicked(m -> playNewGame());
        this.user.addRunnable(() -> {
            this.checkGameStatus(user);

            String move = bot.move(0, 0);
            Class<?> personClass = this.getClass();
            try {
                Field nameField = personClass.getDeclaredField("field" + move);
                nameField.setAccessible(true);
                Label label = (Label) nameField.get(this);
                label.setText(bot.getElement().value());
                nameField.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            this.checkGameStatus(bot);
        });
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
        String text;
        if (player.winGame()) {
            if (player instanceof User) {
                text = "User win, Congrats";
                playerScore.setText(String.valueOf(user.incrementScore()));
            } else {
                text = "Bot win, try again";
                botScore.setText(String.valueOf(bot.incrementScore()));
            }
            paneLabel.setText(text);
            gameOverPane.setVisible(true);
            gameOverPane.setDisable(false);
        }
        if (board.isFull()) {
            paneLabel.setText("Board is full");
            gameOverPane.setVisible(true);
            gameOverPane.setDisable(false);
        }
    }

    private void selectElement() {
        String ui = "X";
        if ("x".equalsIgnoreCase(ui)) {
            user = new User(board, new XElement());
            bot = new Bot(board, new OElement());
        } else if ("o".equalsIgnoreCase(ui)) {
            user = new User(board, new OElement());
            bot = new Bot(board, new XElement());
        } else {
            Printer.print(Errors.USER_ELEMENT_INCORRECT);
            throw new RuntimeException("No user");
        }
    }
}
