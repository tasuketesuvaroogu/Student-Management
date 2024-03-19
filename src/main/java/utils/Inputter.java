package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputter {
    private static final Scanner sc = new Scanner(System.in);
    public static double getDoubleFromInput(String name) {
        double number;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                number = sc.nextDouble();
                sc.nextLine();
                if (number > 0) {
                    return number;
                } else {
                    System.err.println("Please enter the double number > 0 ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Only enter the double number > 0 ");
                sc.next();
            }
        }
    }

    public static int getIntFromInput(String name) {
        int number;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                number = sc.nextInt();
                sc.nextLine();
                if (number > 0) {
                    return number;
                } else {
                    System.err.println("Please enter the integer number > 0 ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Only enter the integer number > 0 ");
                sc.next();
            
            }
        }
    }
    
      public static long getLongFromInput(String name) {
        long number;
        while (true) {
            System.out.print(String.format("Enter %s", name));
            try {
                number = sc.nextLong();
                sc.nextLine();
                if (number > 0) {
                    return number;
                } else {
                    System.err.println("Please enter the number > 0 ");
                }
            } catch (InputMismatchException e) {
                System.err.println("Please enter a valid number");
                sc.next();
            
            }
        }
    }

    public static String getStringFromInput(String name) {
        String string;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                
                string = sc.nextLine();
                if (string.length() > 0) {
                    return string;
                } else {
                    System.err.println("Please enter the string");
                }
            } catch (InputMismatchException e) {
                System.err.println("Only enter the string");
                sc.next();
            }
        }
    }

    public static boolean getBooleanFromInput(String name) {
        boolean bool;
        while (true) {
            System.out.print(String.format("Enter %s: ", name));
            try {
                bool = sc.nextBoolean();
                sc.nextLine();
                return bool;
            } catch (InputMismatchException e) {
                System.err.println("Only enter the boolean");
                sc.next();
            }
        }
    }
}
