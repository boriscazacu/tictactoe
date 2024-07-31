package org.example.player;

import org.example.board.Board;
import org.example.enums.Errors;
import org.example.helpers.Printer;
import org.example.helpers.UserInput;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class User implements Player {

    private final Board board;
    private final Element element;

    public User(Board board, Element element) {
        this.board = board;
        this.element = element;
    }


    @Override
    public Element element() {
        return this.element;
    }

    @Override
    public void move() {
        int x, y, cnt = 0;
        Printer.playerMove(this);
        do {
            if (cnt++ >= 1) {
                Printer.print(Errors.OUT_OF_BOARD);
            }
            x = UserInput.ask() - 1;
            y = UserInput.ask() - 1;
        } while (board.move(x, y, element));
    }

    @Override
    public boolean winGame() {
        return board.playerWinGame(element);
    }
}
