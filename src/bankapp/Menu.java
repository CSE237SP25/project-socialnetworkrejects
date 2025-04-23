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
        handleMenuLoop();
    }

    public void handleMenuLoop() {
        while (true) {
            this.menuDisplayHelper.displayMenuOptions(this.currentUser);
            String menuChoice = this.menuInputHelper.handleUserMenuInput(scanner, currentUser);
            handleUserMenuSelection(menuChoice);
        }
    }

    /**
     * Admin vs Non-admin
     */
    public void handleUserMenuSelection(String menuChoice) {
    	if (currentUser == null && (menuChoice.equalsIgnoreCase("exit program") ||
            menuChoice.equalsIgnoreCase("3"))) {
    	    System.out.println("\nExiting the program.");
    	    System.exit(0);
        }

        if (currentUser == null) {
            handleUnloggedUserMenuSelection(menuChoice);
        } 

        else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                handleAdminMenuSelection(menuChoice);
            } 
            else {
                handleLoggedUserMenuSelection(menuChoice);
            }
        }
    }

    public void handleUnloggedUserMenuSelection(String menuChoice) {
        if (menuChoice.equalsIgnoreCase("register") || menuChoice.equalsIgnoreCase("1")) {
            registerUser();
        } 

        else if (menuChoice.equalsIgnoreCase("login") || menuChoice.equalsIgnoreCase("2")) {
            userLogin();
        } 

        else {
            System.out.println("\nThe selection is invalid.");
        }
    }
    //for admin menu
    public void setFraudThreshold() {
        System.out.println("\nEnter the new fraud threshold: ");
        double newThreshold = scanner.nextDouble();
        scanner.nextLine();
        
        if (newThreshold <= 0) {
            System.out.println("\nInvalid threshold. Please enter a positive number.");
        } else {
            for (User user : users.values()) {
                user.getSavingsAccount().setFraudThreshold(newThreshold);
                if (user.getCheckingAccount() != null) {
                    user.getCheckingAccount().setFraudThreshold(newThreshold);
                }
            }
            System.out.println("\nFraud threshold updated for all accounts.");
        }
    }

    public void handleAdminMenuSelection(String menuChoice) {
        if (menuChoice.equalsIgnoreCase("view all transactions") || menuChoice.equalsIgnoreCase("1")) {
            viewAllTransactions();
        } 
        else if (menuChoice.equalsIgnoreCase("set fraud threshold") || menuChoice.equalsIgnoreCase("2")) {
            setFraudThreshold();
        }
        else if (menuChoice.equalsIgnoreCase("logout") || menuChoice.equalsIgnoreCase("3")) {
            logout();
        } 
        else {
            System.out.println("\nInvalid action.");
        }
    }

    public void handleLoggedUserMenuSelection(String menuChoice) {
        if (menuChoice.equalsIgnoreCase("deposit") || menuChoice.equalsIgnoreCase("1") ) {
            depositMoney();
        } else if (menuChoice.equalsIgnoreCase("withdraw") || menuChoice.equalsIgnoreCase("2")) {
            withdrawMoney();
        } else if (menuChoice.equalsIgnoreCase("transfer") || menuChoice.equalsIgnoreCase("3")) {
            System.out.println("\nTransferring money to another arbitrary savings account.");
            transferMoney();
        } else if (menuChoice.equalsIgnoreCase("history") || menuChoice.equalsIgnoreCase("4")) {
            viewTransactionHistory();
        } else if (menuChoice.equalsIgnoreCase("interest calculator") || menuChoice.equalsIgnoreCase("5")) {
            System.out.println("\nGiving a 1 year calculation of potential interest on money within savings account.");
            System.out.print("Potential interest if money was left for 1 year within savings account: $");
            calculateInterest(1);
        } else if (menuChoice.equalsIgnoreCase("balance") || menuChoice.equalsIgnoreCase("6")) {
            checkBalance();
        } else if (menuChoice.equalsIgnoreCase("open checking account") || menuChoice.equalsIgnoreCase("7")) {
            openCheckingAccount();
        } else if (menuChoice.equalsIgnoreCase("logout") || menuChoice.equalsIgnoreCase("8")) {
            logout();
        } else if (menuChoice.equalsIgnoreCase("exit program") || menuChoice.equalsIgnoreCase("9")) {
            System.out.println("\nExiting the program.");
            System.exit(0);
        } else {
            System.out.println("\nInvalid action.");
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
                System.out.println("\nUsername already exists. Please try again.");
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
     * Admin & non-admin login
     */
    public void userLogin() {
        this.menuDisplayHelper.displayLoginOptions();
        String input = menuInputHelper.handleUserBooleanInput(scanner).trim();

        if (menuInputHelper.checkYes(input)) {
            String username = promptUsername();
            String password = promptPassword();

            if (username.equalsIgnoreCase("admin")) {
                handleAdminPassword(password);
            } 
            else {
                handleNonAdminLogin(username, password);
            }
        } else if (menuInputHelper.checkNo(input)) {
            runStartingConfiguration();
        } else {
            System.out.println("Invalid input");
            userLogin();
        }
    }

    public void handleNonAdminLogin(String username, String password) {
        if (users.containsKey(username)) {
            validateAndLoginUser(username, password);
            if (currentUser != null) {
                return;
            }
        } 
        else {
            handleNormalUserPassword(username, password);
        }
    }

    public void handleAdminPassword(String password) {
        if (password.equals("xyz")) {
            currentUser = new User("admin", "xyz");
            System.out.println("\n" + "Admin user logged in successfully.");
        } else {
            System.out.println("Invalid admin credentials.");
        }

    }

    public void handleNormalUserPassword(String username, String password) {
        System.out.println("\n" + "User does not exist. In order to log in, you must first register. \n");
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
     * Personal user's transaction history
     */
    public void viewTransactionHistory() {
        System.out.println("\nNow viewing personal transaction history, with a unique ID for each transaction.");
        ArrayList<String> history = currentUser.getSavingsAccount().getTransactionHistory();

        if (history.isEmpty()) {
            System.out.println("\nYou have not made any transactions.");
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
        System.out.println("\nNow viewing all user transactions, with a unique ID for each transaction.");

        if (users.isEmpty()) {
            System.out.println("No users, so the global transaction history cannot be accessed.");
            return;
        }

        for (String username : users.keySet()) {
            User user = users.get(username);
            System.out.println("\nUser: " + username);

            ArrayList<String> history = user.getSavingsAccount().getTransactionHistory();
            if (history.isEmpty()) {
                System.out.println("This user currently has no transactions.");
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
        this.menuDisplayHelper.displayDepositOptions(currentUser);

        int accountChoice = handleAccountChoiceOverall("Choose an account to deposit into:");
        double amount = positiveAmountHelper("Enter amount to deposit:");

        try {
            handleAccountDepositChoice(accountChoice, amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void handleAccountDepositChoice(int accountChoice, double amount) {
        if (accountChoice == 1) {
            currentUser.getSavingsAccount().deposit(amount);
            System.out.println("\nDeposit successful to Savings Account.");
        }
        else if (accountChoice == 2 && currentUser.getCheckingAccount() != null) {
            currentUser.getCheckingAccount().deposit(amount);
            System.out.println("\nDeposit successful to Checking Account.");
        }
        else {
            System.out.println("Invalid choice. Please choose a valid account");
        }
    }

    /**
     * Withdraw
     */
    public void withdrawMoney() {
        this.menuDisplayHelper.displayWithdrawOptions(currentUser);
        int accountChoice = handleAccountChoiceOverall("Choose an account to withdraw from:");
        double amount = positiveAmountHelper("Enter amount to withdraw:");

        try {
            handleAccountWithdrawChoice(accountChoice, amount);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void handleAccountWithdrawChoice(int accountChoice, double amount) {
        if (accountChoice == 1) {
            currentUser.getSavingsAccount().withdraw(amount);
            System.out.println("\nWithdrawal successful from Savings Account.");
        } else if (accountChoice == 2 && currentUser.getCheckingAccount() != null) {
            currentUser.getCheckingAccount().withdraw(amount);
            System.out.println("\nWithdrawal successful from Checking Account.");
        } else {
            System.out.println("\nInvalid choice");
        }
    }

    public void transferMoney() {
        this.menuDisplayHelper.displayTransferOptions(currentUser);

        int accountChoice = handleAccountChoiceOverall("Choose an account to transfer from:");
        double amount = positiveAmountHelper("Enter amount to transfer:");
        String recipientUsername = handleValidRecipient("Enter the username of the recipient:");

        handleAccountTransferChoice(accountChoice, amount, recipientUsername);
    }

    private String handleValidRecipient(String promptMessage) {
        while (true) {
            System.out.println("\n" + promptMessage);
            String recipientUsername = scanner.nextLine().trim();

            if (recipientUsername.equals(currentUser.getUsername())) {
                System.out.println("You cannot transfer money to yourself.");
                continue;
            }
            if (!users.containsKey(recipientUsername)) {
                System.out.println("The recipient user does not exist in the system.");
                continue;
            }
            return recipientUsername;
        }
    }


    public void handleAccountTransferChoice(int accountChoice, double amount, String recipientUsername) {
        User recipient = users.get(recipientUsername);
        if (recipient == null) {
            System.out.println("\nThe recipient user does not exist in the system.");
            return;
        }
        if (recipientUsername.equals(currentUser.getUsername())) {
            System.out.println("\nYou cannot transfer money to yourself.");
            return;
        }
        if (accountChoice == 1) {
            currentUser.getSavingsAccount().transfer(amount, recipient.getSavingsAccount());
            System.out.println("\nTransfer successful from Savings Account to " + recipientUsername + "'s Savings Account.");
        } else if (accountChoice == 2 && currentUser.getCheckingAccount() != null) {
            currentUser.getCheckingAccount().transfer(amount, recipient.getSavingsAccount());
            System.out.println("\nWithdrawal successful from Checking Account to " + recipientUsername + "'s Savings Account");
        } else {
            System.out.println("\nInvalid choice");
        }

    }
    private int handleAccountChoiceOverall(String promptMessage) {
        while (true) {
            System.out.println("\n" + promptMessage);
            String input = scanner.nextLine().trim().toLowerCase();
            int choice = handleAccountChoiceParser(input);
            if (choice != -1) return choice;
            System.out.println("Invalid choice. Please choose a valid account.");
        }
    }
    private int handleAccountChoiceParser(String input) {
        if (input.equals("1") || input.equals("savings") || input.equals("savings account")) {
            return 1;
        } else if (input.equals("2") || input.equals("checking") || input.equals("checking account")) {
            return 2;
        } else {
            return -1;
        }
    }

    private double positiveAmountHelper(String promptMessage) {
        while (true) {
            System.out.println("\n" + promptMessage);
            try {
                double amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount > 0) return amount;
                System.out.println("Amount must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
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