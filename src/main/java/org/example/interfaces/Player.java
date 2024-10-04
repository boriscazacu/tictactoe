package org.example.interfaces;

public interface Player {
    String move(int x, int y);
    boolean winGame();
    int incrementScore();
}
