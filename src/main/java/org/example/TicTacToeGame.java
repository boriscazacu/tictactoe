package org.example;

import lombok.Data;
import org.example.board.OElement;
import org.example.board.XElement;
import org.example.enums.Errors;
import org.example.helpers.UserInput;
import org.example.interfaces.Game;
import org.example.board.Board;
import org.example.helpers.Printer;
import org.example.interfaces.Player;
import org.example.player.Bot;
import org.example.player.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicTacToeGame implements Game {
    private int boardSize = 3;
    private final Board board;
    private final List<Player> players = new ArrayList<>(2);

    /**
     * Init with default board size
     */
    public TicTacToeGame() {
        this.board = new Board(boardSize);
    }

    public TicTacToeGame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
    }


    @Override
    public void start() {
        Printer.welcomeMessage();
        selectElement();
        while (true) {
            for (Player player : players) {
                player.move();
                Printer.print(board);
                if (player.winGame()) {
                    Printer.playerWin(player);
                    return;
                }
            }
            if (board.isFull()) {
                Printer.boardFull();
                break;
            }
        }
    }

    private void selectElement() {
        int count = 0, maxCount = 10;
        while (count < maxCount) {
            String ui = UserInput.askSelectOption();
            if ("x".equalsIgnoreCase(ui)) {
                players.add(new User(board, new XElement()));
                players.add(new Bot(board, new OElement()));
                break;
            } else if ("o".equalsIgnoreCase(ui)) {
                players.add(new User(board, new OElement()));
                players.add(new Bot(board, new XElement()));
                break;
            } else {
                Printer.print(Errors.USER_ELEMENT_INCORRECT);
            }
            count++;
        }
        Printer.print(board);
    }
}
