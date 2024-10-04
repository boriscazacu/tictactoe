package org.example.player;

import org.example.board.Board;
import org.example.helpers.Printer;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class Bot implements Player {

    private final Board board;
    private final Element element;
    private int score = 0;

    public Bot(Board board, Element element) {
        this.board = board;
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String move(int x, int y) {
        do {
            x = (int) (Math.random() * board.getSize());
            y = (int) (Math.random() * board.getSize());
        } while (board.move(x, y, element));

        return String.format("%d%d", x, y);
    }

    @Override
    public boolean winGame() {
        return board.playerWinGame(element);
    }

    @Override
    public int incrementScore() {
        return ++score;
    }
}
