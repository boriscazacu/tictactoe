package org.example.helpers;

import lombok.experimental.UtilityClass;
import org.example.board.Board;
import org.example.enums.Errors;
import org.example.interfaces.Player;
import org.example.player.User;

@UtilityClass
public class Printer {

    public static void welcomeMessage() {
        System.out.println("Welcome to the Tic Tac Toe game!");
    }

    public static void print(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                System.out.print(board.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void boardFull() {
        System.out.println("Board is full, no wins");
    }

    public static void playerWin(Player player) {
        if (player instanceof User) {
            System.out.println("-----------------------------------");
            System.out.println("*** You win, Congratulations! ***");
            System.out.println("-----------------------------------");
        } else {
            System.out.println("-----------------------------------");
            System.out.println("*** Bot win, try again ***");
            System.out.println("-----------------------------------");
        }
    }

    public static void playerMove(Player player) {
        if (player instanceof User) {
            System.out.println("User move. Please enter the X & Y coordinate: ");
        } else {
            System.out.println("Bot move:");
        }
    }

    public static void print(Errors errors) {
        switch (errors) {
            case USER_ELEMENT_INCORRECT -> System.out.println("Incorrect user element. Please write X or Y !");
            case OUT_OF_BOARD -> System.out.println("You are out of board. Please write value lower than board side !");
            default -> System.out.println("Internal error. Please try again.");
        }
    }
}
