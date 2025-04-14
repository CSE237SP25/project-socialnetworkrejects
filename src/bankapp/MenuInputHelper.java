package bankapp;

import java.util.Scanner;

public class MenuInputHelper {

    public String handleUserMenuInput(Scanner scanner, User currentUser) {
        String input = scanner.nextLine().toLowerCase();
        while (checkIncorrectUserMenuInput(input, currentUser)) {
            System.out.println("Please enter a correct menu selection.");
            input = scanner.nextLine().toLowerCase();
        }
        return input;
    }

    public boolean checkIncorrectUserMenuInput(String input, User currentUser) {
        String lowerInput = input.toLowerCase();
        if (currentUser == null) {
            return !(lowerInput.equals("register") || lowerInput.equals("a") ||lowerInput.equals("login") || lowerInput.equals("b"));
        } else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                return !lowerInput.equals("logout");
            } else {
                return !(lowerInput.equals("1") || lowerInput.equals("2") || lowerInput.equals("3") ||
                        lowerInput.equals("4") || lowerInput.equals("5") || lowerInput.equals("6") || lowerInput.equals("7"));
            }
        }
    }

    public String handleUserBooleanInput(Scanner scanner) {
        String input = scanner.nextLine().toLowerCase();
        while (checkIncorrectUserBooleanInput(input)) {
            System.out.println("Please enter a correct menu selection");
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
