package bankapp;

import java.util.Scanner;

public class MenuInputHelper {

    public String handleUserMenuInput(Scanner scanner, User currentUser) {
        String input = scanner.nextLine().toLowerCase();
        while (checkIncorrectUserMenuInput(input, currentUser)) {
            System.out.println("\nPlease enter a correct menu selection.");
            input = scanner.nextLine().toLowerCase();
        }
        return input;
    }

    public boolean checkIncorrectUserMenuInput(String input, User currentUser) {
        String lowerInput = input.toLowerCase();

        if (currentUser == null) {
            return !(lowerInput.equals("register") || lowerInput.equals("login") || lowerInput.equals("exit program") ||
                    lowerInput.equals("1") || lowerInput.equals("2") || lowerInput.equals("3") );
        } else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                return !(lowerInput.equals("logout") || lowerInput.equals("view all transactions") || lowerInput.equals("set fraud threshold") ||lowerInput.equals("exit program"));
            } else {
                return !(lowerInput.equals("deposit") || lowerInput.equals("withdraw") || lowerInput.equals("transfer") ||
                        lowerInput.equals("history") || lowerInput.equals("interest calculator") ||
                        lowerInput.equals("balance") || lowerInput.equals("open checking account") ||
                        lowerInput.equals("logout") || lowerInput.equals("exit program") ||
                        lowerInput.equals("1") || lowerInput.equals("2") || lowerInput.equals("3") ||
                        lowerInput.equals("4") || lowerInput.equals("5") || lowerInput.equals("6") ||
                        lowerInput.equals("7") || lowerInput.equals("8") || lowerInput.equals("9"));
            }
        }
    }

    public String handleUserBooleanInput(Scanner scanner) {
        String input = scanner.nextLine().toLowerCase();
        while (checkIncorrectUserBooleanInput(input)) {
            System.out.println("\nPlease enter a correct menu selection.");
            input = scanner.nextLine().toLowerCase();
        }
        return input;
    }

    public boolean checkIncorrectUserBooleanInput(String input) {
        String lowerInput = input.toLowerCase();
        return !(lowerInput.equals("yes") || lowerInput.equals("no") ||
                lowerInput.equals("1") || lowerInput.equals("2") ||
                lowerInput.equals("3") || lowerInput.equals("4") ||
                lowerInput.equals("5") || lowerInput.equals("6") ||
                lowerInput.equals("7") || lowerInput.equals("8") ||
                lowerInput.equals("9"));
    }

    public boolean checkYes(String input) {
        return input.equalsIgnoreCase("yes") || input.equals("1");
    }

    public boolean checkNo(String input) {
        return input.equalsIgnoreCase("no") || input.equals("2");
    }
}