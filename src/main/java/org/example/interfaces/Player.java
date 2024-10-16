package org.example.interfaces;

import org.example.board.Board;

public interface Player {
    String name();
    String move(Board board, int x, int y);
    boolean winGame(Board board);
    Element getElement();
    Player increment();
    int getScore();
}
