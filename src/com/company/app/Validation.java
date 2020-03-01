package com.company.app;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Validation {
    private static Scanner scan = new Scanner(System.in);
    private static String input;

    public static String string(String prompt) {
        System.out.print(prompt);
        try {
            input = scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("#invalid input#");
            string(prompt);
        }
        return input;
    }

    public static double floatingNumber(String prompt) {
        double inputDouble = -1;
        boolean everythingIsFine;
        do {
            System.out.print(prompt);
            try {
                input = scan.nextLine();
                inputDouble = Double.parseDouble(input);
                everythingIsFine = true;
            } catch (NumberFormatException e) {
                System.out.println("#invalid input#");
                everythingIsFine = false;
            }
        } while (!everythingIsFine);
        return inputDouble;
    }

    public static long longNumber(String prompt) {
        long inputLong = -1;
        boolean everythingIsFine;
        do {
            System.out.print(prompt);
            try {
                input = scan.nextLine();
                inputLong = Long.parseLong(input);
                everythingIsFine = true;
            } catch (NumberFormatException e) {
                System.out.println("#invalid input#");

                everythingIsFine = false;
            }
        } while (!everythingIsFine);
        return inputLong;
    }




    }










