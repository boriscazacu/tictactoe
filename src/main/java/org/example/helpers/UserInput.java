package org.example.helpers;


import java.util.Scanner;

public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    private UserInput() {}

    public static int ask() {
        return SCANNER.nextInt();
    }

    public static String askSelectOption() {
        System.out.println("Please select what you want to play with X or O:");
        return SCANNER.nextLine();
    }

}
