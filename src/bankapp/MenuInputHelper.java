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
            return !(lowerInput.equals("register") || lowerInput.equals("login") || lowerInput.equals("exit program"));
        } else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                return !(lowerInput.equals("logout") || lowerInput.equals("view all transactions") || lowerInput.equals("exit program"));
            } else {
                return !(lowerInput.equals("deposit") || lowerInput.equals("withdraw") || lowerInput.equals("transfer") || lowerInput.equals("history") || lowerInput.equals("interest calculator") || lowerInput.equals("balance") || lowerInput.equals("open checking account") || lowerInput.equals("logout") || lowerInput.equals("exit program"));
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
        return !(lowerInput.equals("yes") || lowerInput.equals("no"));
    }

    public boolean checkYes(String input) {
        return input.equalsIgnoreCase("yes");
    }

    public boolean checkNo(String input) {
        return input.equalsIgnoreCase("no");
    }

}
