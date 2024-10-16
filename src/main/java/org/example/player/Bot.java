package org.example.player;

import org.example.board.Board;
import org.example.interfaces.Element;
import org.example.interfaces.Player;

public class Bot extends Player {

    public Bot(Element element) {
        super(element);
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
}
