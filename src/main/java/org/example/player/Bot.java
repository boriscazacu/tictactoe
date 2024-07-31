package org.example.player;

import org.example.board.Board;
import org.example.helpers.Printer;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class Bot implements Player {

    private final Board board;
    private final Element element;

    public Bot(Board board, Element element) {
        this.board = board;
        this.element = element;
    }

    @Override
    public Element element() {
        return element;
    }

    @Override
    public void move() {
        int x, y;
        Printer.playerMove(this);
        do {
            x = (int) (Math.random() * board.getSize());
            y = (int) (Math.random() * board.getSize());
        } while (board.move(x, y, element));
    }

    @Override
    public boolean winGame() {
        return board.playerWinGame(element);
    }
}
