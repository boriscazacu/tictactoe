package org.example.player;

import org.example.board.Board;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class User implements Player {
    private final Element element;
    private int score = 0;
    private String userName = "User";

    public User(Element element) {
        this.element = element;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @Override
    public String name() {
        return userName;
    }

    @Override
    public String move(Board board, int x, int y) {
        board.move(x, y, element);
        return element.value();
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
    public Element getElement() {
        return this.element;
    }

    @Override
    public int getScore() {
        return score;
    }
}
