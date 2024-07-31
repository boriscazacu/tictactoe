package org.example.helpers;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class UserInput {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int ask() {
        return SCANNER.nextInt();
    }

    public static String askSelectOption() {
        System.out.println("Please select what you want to play with X or O:");
        return SCANNER.nextLine();
    }

}
