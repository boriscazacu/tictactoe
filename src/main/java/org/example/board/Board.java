package org.example.board;

import org.example.interfaces.Element;

public class Board {
    private static final String EMPTY_CELL = " * ";
    private final int size;
    private String[][] board;

    public Board(int size) {
        this.size = size;
        this.iniBoard();
    }

    private void iniBoard() {
        this.board = new String[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = EMPTY_CELL;
            }
        }
    }

    public String get(int row, int col) {
        return this.board[row][col];
    }

    public boolean move(int row, int col, Element element) {
        if (row < 0 || row >= this.size || col < 0 || col >= this.size) {
            return true;
        }
        if (this.board[row][col].equals(EMPTY_CELL)) {
            this.board[row][col] = " " + element.value() + " ";
            return false;
        }
        return true;
    }

    public boolean isFull() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j].equals(EMPTY_CELL)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean playerWinGame(Element element) {
        boolean row, col, diag1 = true, diag2 = true;
        for (int i = 0; i < this.size; i++) {
            row = this.board[i][0].contains(element.value());
            col = this.board[0][i].contains(element.value());
            for (int j = 1; j < this.size; j++) {
                row = row && this.board[i][j].contains(element.value());
                col = col && this.board[j][i].contains(element.value());
            }
            if (row || col) {
                return true;
            }
        }
        for (int i = 0; i < this.size; i++) {
            diag1 = diag1 && this.board[i][i].contains(element.value());
        }
        for (int i = 0; i < this.size; i++) {
            diag2 = diag2 && this.board[i][size - (i + 1)].contains(element.value());
        }
        return diag1 || diag2;
    }

    public int getSize() {
        return size;
    }

    public String[][] getBoard() {
        return board;
    }

    public void clean() {
        this.iniBoard();
    }
}
