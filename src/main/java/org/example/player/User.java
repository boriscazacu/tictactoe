package org.example.player;

import org.example.board.Board;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class User extends Player {

    public User(Element element) {
        super(element);
    }

    @Override
    public String move(Board board, int x, int y) {
        board.move(x, y, element);
        return element.value();
    }
}
