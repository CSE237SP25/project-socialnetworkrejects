package bankapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private HashMap<String, User> users;
    private User currentUser;
    private MenuDisplayHelper menuDisplayHelper;
    private MenuInputHelper menuInputHelper;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.users = new HashMap<>();
        this.currentUser = null;
        this.menuDisplayHelper = new MenuDisplayHelper();
        this.menuInputHelper = new MenuInputHelper();
    }

    public void runStartingConfiguration() {
        this.menuDisplayHelper.displayWelcomeMessage();
        while (true) {
            this.menuDisplayHelper.displayMenuOptions(this.currentUser);
            String menuChoice = this.menuInputHelper.handleUserMenuInput(scanner, currentUser);
            handleUserMenuSelection(menuChoice);
        }
    }

    /**
     * Admin vs nonadmin
     */
    public void handleUserMenuSelection(String menuChoice) {
        if (currentUser == null) {
            handleUnloggedUserMenuSelection(menuChoice);
        } else {
            // Logged in

            //The user is an admin
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                handleAdminMenuSelection(menuChoice);
            } 
            //The user is a normal user
            else {
                handleLoggedUserMenuSelection(menuChoice);
            }
        }
    }

    public void handleUnloggedUserMenuSelection(String menuChoice) {
        // No one is logged in -> REGISTER or LOGIN.
        if (menuChoice.equalsIgnoreCase("register")) {
            registerUser();
        } else if (menuChoice.equalsIgnoreCase("login")) {
            userLogin();
        } else {
            System.out.println("The selection is invalid.");
        }
    }

    public void handleAdminMenuSelection(String menuChoice) {
        if (menuChoice.equalsIgnoreCase("view all transactions")) {
            viewAllTransactions();
        } else if (menuChoice.equalsIgnoreCase("logout")) {
            logout();
        } else {
            System.out.println("Invalid action.");
        }
        
    }

    public void handleLoggedUserMenuSelection(String menuChoice) {
        if (menuChoice.equalsIgnoreCase("deposit")) {
            depositMoney();
        } else if (menuChoice.equalsIgnoreCase("withdraw")) {
            withdrawMoney();
        } else if (menuChoice.equalsIgnoreCase("history")) {
            viewTransactionHistory();
        } else if (menuChoice.equalsIgnoreCase("interest calculator")) {
            System.out.println("\nGiving a 1 year calculation of interest.");
            System.out.print("Interest generated in 1 year: $");
            calculateInterest(1); // Placeholder for interest calculation
        } else if (menuChoice.equalsIgnoreCase("balance")) {
            checkBalance();
        } else if (menuChoice.equalsIgnoreCase("open checking account")) {
            openCheckingAccount();
        } else if (menuChoice.equalsIgnoreCase("logout")) {
            logout();
        } else {
            System.out.println("Invalid action");
        }
    }

    /**
     * Creates a new user
     */
    public void registerUser() {
        this.menuDisplayHelper.displayRegisterOptions();
        if (menuInputHelper.checkYes(menuInputHelper.handleUserBooleanInput(scanner))) {
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
            System.out.println("\n" + "Successfully logged out user " + currentUser.getUsername()+ "." + "\n");
            currentUser = null;
        } else {
            System.out.println("\n" + "User is not logged in." + "\n");
        }
    }

    /**
     * Admin & normal
     */
    public void userLogin() {
        this.menuDisplayHelper.displayLoginOptions();
        if (menuInputHelper.checkYes(menuInputHelper.handleUserBooleanInput(scanner))) {
            String username = promptUsername();
            String password = promptPassword();
            // If admin
            if (username.equalsIgnoreCase("admin")) {
                if (password.equals("xyz")) {
                    currentUser = new User("admin", "xyz");
                    System.out.println("\n" + "Admin user logged in successfully.");
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
            System.out.println("\n" + "Logged in successfully as " + username + ".");
        } else {
            System.out.println("\n" + "Incorrect password");
        }
    }
    /**
     * Transaction history
     */
    public void viewTransactionHistory() {
        System.out.println("Transaction History");
        ArrayList<String> history = currentUser.getSavingsAccount().getTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("No Transactions Made");
        } else {
            for (String entry : history) {
                System.out.println("- " + entry);
            }
        }
    }
    
    /**
     * Admin can view all transactions
     */
    public void viewAllTransactions() {
        System.out.println("All User Transactions");

        if (users.isEmpty()) {
            System.out.println("No Users");
            return;
        }

        for (String username : users.keySet()) {
            User user = users.get(username);
            System.out.println("\nUser: " + username);

            ArrayList<String> history = user.getSavingsAccount().getTransactionHistory();
            if (history.isEmpty()) {
                System.out.println(" No Transactions");
            } else {
                for (String entry : history) {
                    System.out.println("  - " + entry);
                }
            }
        }
    }



    /**
     * Creates the user
     */
    private void createUser(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
        currentUser = newUser;
        System.out.print("\n");
        System.out.println("Created and logged into new account!");
    }

    /**
     * Deposit
     */
    public void depositMoney() {
        System.out.println("\nChoose an account to deposit into:");
        System.out.println("1. Savings Account");
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("2. Checking Account");
        } else {
            System.out.println("2. Checking Account (Not Opened)");
        }

        int accountChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        System.out.println("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline character

        try {
            if (accountChoice == 1) {
                currentUser.getSavingsAccount().deposit(amount);
                System.out.println("\nDeposit successful to Savings Account.");
            } else if (accountChoice == 2 && currentUser.getCheckingAccount() != null) {
                currentUser.getCheckingAccount().deposit(amount);
                System.out.println("\nDeposit successful to Checking Account.");
            } else {
                System.out.println("Invalid account choice or Checking Account not opened.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Withdraw
     */
    public void withdrawMoney() {
        System.out.println("\nChoose an account to withdraw from:");
        System.out.println("1. Savings Account");
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("2. Checking Account");
        } else {
            System.out.println("2. Checking Account (Not Opened)");
        }

        int accountChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        System.out.println("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline character

        try {
            if (accountChoice == 1) {
                currentUser.getSavingsAccount().withdraw(amount);
                System.out.println("\nWithdrawal successful from Savings Account.");
            } else if (accountChoice == 2 && currentUser.getCheckingAccount() != null) {
                currentUser.getCheckingAccount().withdraw(amount);
                System.out.println("\nWithdrawal successful from Checking Account.");
            } else {
                System.out.println("Invalid account choice or Checking Account not opened.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *User balance
     */
    public void checkBalance() {
        System.out.print("\n");
        double savingsBalance = currentUser.getSavingsAccount().getCurrentBalance();
        System.out.println("Savings Account Balance: $" + String.format("%.2f", savingsBalance));
        
        if (currentUser.getCheckingAccount() != null) {
            double checkingBalance = currentUser.getCheckingAccount().getCurrentBalance();
            System.out.println("Checking Account Balance: $" + String.format("%.2f", checkingBalance));
        } else {
            System.out.println("Checking Account: Not opened yet.");
        }
    }

    public String promptUsername() {
    	System.out.print("\n");
        System.out.println("Enter Username: ");
        return scanner.nextLine();
    }

    public String promptPassword() {
    	System.out.print("\n");
        System.out.println("Enter Password: ");
        return scanner.nextLine();
    }

    //Helper Methods:
    public void registerUserForTest(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
        currentUser = newUser;
    }
    public void deposit(double amount) {
        currentUser.getSavingsAccount().deposit(amount);
    }
    public void withdraw(double amount) {
        currentUser.getSavingsAccount().withdraw(amount);
    }
    public void calculateInterest(double years) {
        System.out.println(currentUser.getSavingsAccount().calculateInterest(years));
    }
    public double getCurrentBalance() {
        return currentUser.getSavingsAccount().getCurrentBalance();
    }
    public void openCheckingAccount() {
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("\nYou already have a checking account.");
        } else {
            currentUser.openCheckingAccount();
            System.out.println("\nChecking account opened successfully!");
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }
}