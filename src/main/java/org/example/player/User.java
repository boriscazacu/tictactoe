package org.example.player;

import org.example.board.Board;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class User implements Player {

    private final Board board;
    private final Element element;
    private Runnable runnable;
    private int score = 0;

    public void addRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public User(Board board, Element element) {
        this.board = board;
        this.element = element;
    }

    @Override
    public String move(int x, int y) {
        board.move(x, y, element);
        if (this.runnable != null) {
            this.runnable.run();
        }
        return element.value();
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
