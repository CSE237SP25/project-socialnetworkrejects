package bankapp;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private HashMap<String, User> users;
    private User currentUser;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.users = new HashMap<>();
        this.currentUser = null;
    }

    public void runStartingConfiguration() {
        displayWelcomeMessage();
        while (true) {
            displayMenuOptions();
            String menuChoice = handleUserMenuInput();
            handleUserMenuSelection(menuChoice);
        }
    }

    /**
     * Admin vs nonadmin
     */
    public void handleUserMenuSelection(String menuChoice) {
        if (currentUser == null) {
            // No one is logged in -> REGISTER or LOGIN.
            if (menuChoice.equalsIgnoreCase("register")) {
                registerUser();
            } else if (menuChoice.equalsIgnoreCase("login")) {
                userLogin();
            } else {
                System.out.println("Act is invalid");
            }
        } else {
        	//Logged in
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                // Admin
                if (menuChoice.equalsIgnoreCase("logout")) {
                    logout();
                } else {
                    System.out.println("logout");
                }
            } else {
                if (menuChoice.equalsIgnoreCase("deposit")) {
                    depositMoney();
                } else if (menuChoice.equalsIgnoreCase("withdraw")) {
                    withdrawMoney();
                } else if (menuChoice.equalsIgnoreCase("balance")) {
                    checkBalance();
                } else if (menuChoice.equalsIgnoreCase("logout")) {
                    logout();
                } else {
                    System.out.println("Invalid action");
                }
            }
        }
    }

    /**
     * Main menu
     */
    public void displayMenuOptions() {
        if (currentUser == null) {
            System.out.println("[ Menu Options ]");
            System.out.println("> Register");
            System.out.println("> Login");
        } else {
            if (currentUser.getUsername().equalsIgnoreCase("Admin")) {
                System.out.println("Welcome, Admin!");
                System.out.println("[ Menu Options ]");
                System.out.println("> Logout");
            } else {
                System.out.println("Welcome, " + currentUser.getUsername() + "!");
                System.out.println("[Menu Options ]");
                System.out.println("> Deposit");
                System.out.println("> Withdraw");
                System.out.println("> Balance");
                System.out.println("> Logout");
            }
        }
    }

    /**
     * Welcome message
     */
    public void displayWelcomeMessage() {
    	System.out.print("\n");
        System.out.println("Welcome to the Bank Social Network.");
    }

    /**
     * Creates a new user
     */
    public void registerUser() {
        displayRegisterOptions();
        if (checkYes(handleUserBooleanInput())) {
            String username = promptUsername();
            if (users.containsKey(username)) {
                System.out.println("Username already exists. Please try again.");
                return;
            }
            String password = promptPassword();
            createUser(username, password);
        } else {
            runStartingConfiguration();
        }
    }

    /**
     * Logs out
     */
    public void logout() {
        if (currentUser != null) {
            System.out.println("Logged out: " + currentUser.getUsername());
            currentUser = null;
        } else {
            System.out.println("User is not logged in.");
        }
    }

    /**
     * Admin & normal
     */
    public void userLogin() {
        displayLoginOptions();
        if (checkYes(handleUserBooleanInput())) {
            String username = promptUsername();
            String password = promptPassword();
            // If admin
            if (username.equalsIgnoreCase("admin")) {
                if (password.equals("xyz")) {
                    currentUser = new User("admin", "xyz");
                    System.out.println("Admin logged in successfully.");
                } else {
                    System.out.println("Invalid admin credentials.");
                }
            } else {
                // Normal user
                if (!users.containsKey(username)) {
                    System.out.println("User not found. Try again.");
                    return;
                }
                validateAndLoginUser(username, password);
            }
        } else {
            runStartingConfiguration();
        }
    }

    /**
     * Password validation
     */
    private void validateAndLoginUser(String username, String password) {
        User user = users.get(username);
        if (user.validatePassword(password)) {
            currentUser = user;
            System.out.println("Logged in successfully as: " + username);
        } else {
            System.out.println("Incorrect password");
        }
    }

    /**
     * Creates the user
     */
    private void createUser(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
        currentUser = newUser;
        System.out.println("Created and logged into new account!");
    }

    /**
     * Deposit
     */
    public void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline
        try {
            currentUser.getAccount().deposit(amount);
            System.out.println("Deposit successful.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Withdraw
     */
    public void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline
        try {
            currentUser.getAccount().withdraw(amount);
            System.out.println("Withdrawal completed!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *User balance
     */
    public void checkBalance() {
        System.out.println("Current balance: $" + currentUser.getAccount().getCurrentBalance());
    }

    public String promptUsername() {
        System.out.println("Enter Username: ");
        return scanner.nextLine();
    }

    public String promptPassword() {
        System.out.println("Enter Password: ");
        return scanner.nextLine();
    }

    public void displayLoginOptions() {
        System.out.println("Are you sure you want to login?");
        System.out.println("YES");
        System.out.println("NO");
    }

    public void displayRegisterOptions() {
        System.out.println("Are you sure you want to register?");
        System.out.println("YES");
        System.out.println("NO");
    }

    public String handleUserMenuInput() {
        String input = scanner.nextLine().toLowerCase();
        while (checkIncorrectUserMenuInput(input)) {
            System.out.println("Please enter a correct menu selection.");
            input = scanner.nextLine().toLowerCase();
        }
        return input;
    }

    public boolean checkIncorrectUserMenuInput(String input) {
        String lowerInput = input.toLowerCase();
        if (currentUser == null) {
            return !(lowerInput.equals("register") || lowerInput.equals("login"));
        } else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                return !lowerInput.equals("logout");
            } else {
                return !(lowerInput.equals("deposit") || lowerInput.equals("withdraw") ||
                         lowerInput.equals("balance") || lowerInput.equals("logout"));
            }
        }
    }

    public String handleUserBooleanInput() {
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

    //Helper Methods:
    public void registerUserForTest(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
        currentUser = newUser;
    }
    public void deposit(double amount) {
        currentUser.getAccount().deposit(amount);
    }
    public void withdraw(double amount) {
        currentUser.getAccount().withdraw(amount);
    }
    public double getCurrentBalance() {
        return currentUser.getAccount().getCurrentBalance();
    }
}
