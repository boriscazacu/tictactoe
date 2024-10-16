package org.example.interfaces;

import org.example.board.Board;

public abstract class Player {
    protected final Element element;
    private String userName = "User";
    private int score = 0;

    protected Player(Element element) {
        this.element = element;
    }

    public abstract String move(Board board, int x, int y);

    public Player setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public boolean winGame(Board board) {
        return board.playerWinGame(element);
    }

    public String name() {
        return userName;
    }
    public Element getElement() {
        return element;
    }

    public Player increment() {
        score++;
        return this;
    }

    public int getScore() {
        return score;
    }
}
