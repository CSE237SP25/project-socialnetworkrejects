package bankapp;

public class MenuDisplayHelper {

    public void displayDepositOptions(User currentUser) {
        System.out.println("\nChoose an account to deposit into:");
        System.out.println("1. Savings Account");
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("2. Checking Account");
        } else {
            System.out.println("2. Checking Account (Not Opened)");
        }
    }

    public void displayWithdrawOptions(User currentUser) {
        System.out.println("\nChoose an account to withdraw from:");
        System.out.println("1. Savings Account");
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("2. Checking Account");
        } else {
            System.out.println("2. Checking Account (Not Opened)");
        }
    }

    public void displayTransferOptions(User currentUser) {
        System.out.println("\nChoose an account to transfer from:");
        System.out.println("1. Savings Account");
        if (currentUser.getCheckingAccount() != null) {
            System.out.println("2. Checking Account");
        } else {
            System.out.println("2. Checking Account (Not Opened)");
        }
    }

    public void displayLoginOptions() {
    	System.out.print("\n");
        System.out.println("Are you sure you want to login?");
        System.out.print("\n");
        System.out.println("> Yes");
        System.out.println("> No");
    }

    public void displayRegisterOptions() {
    	System.out.print("\n");
        System.out.println("Are you sure you want to register?");
        System.out.print("\n");
        System.out.println("> Yes");
        System.out.println("> No");
    }

        /**
     * Main menu
     */
    public void displayMenuOptions(User currentUser) {
        // user not logged in
        if (currentUser == null) {
            System.out.println("[ Menu Options ]");
            System.out.println("> Register");
            System.out.println("> Login");
        } 
        // If a user is logged in
        else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                System.out.println("\n" + "Welcome, admin user!");
                System.out.println("[ Menu Options ]");
                System.out.println("> View All Transactions");
                System.out.println("> Logout");
            } else {
                System.out.println("\n" + "Welcome, " + currentUser.getUsername() + "!");
                System.out.println("[ Menu Options ]");
                System.out.println("> Deposit");
                System.out.println("> Withdraw");
                System.out.println("> Transfer");
                System.out.println("> History");
                System.out.println("> Interest Calculator");
                System.out.println("> Balance");
                System.out.println("> Open Checking Account");
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
        System.out.print("\n");
    }
    
}
