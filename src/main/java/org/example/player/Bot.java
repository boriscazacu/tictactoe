package org.example.player;

import org.example.board.Board;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class Bot implements Player {

    private final Element element;
    private int score = 0;

    public Bot(Element element) {
        this.element = element;
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String name() {
        return "Bot";
    }

    @Override
    public String move(Board board, int x, int y) {
        do {
            x = (int) (Math.random() * board.getSize());
            y = (int) (Math.random() * board.getSize());
        } while (board.move(x, y, element));

        return String.format("%d%d", x, y);
    }

    @Override
    public boolean winGame(Board board) {
        return board.playerWinGame(element);
    }

    @Override
    public Player increment() {
        score++;
        return this;
    }

    @Override
    public int getScore() {
        return score;
    }
}
