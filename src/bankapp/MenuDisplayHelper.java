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
            System.out.println("> A. Register");
            System.out.println("> B. Login");
        } 
        // If a user is logged in
        else {
            if (currentUser.getUsername().equalsIgnoreCase("admin")) {
                System.out.println("\n" + "Welcome, admin user!");
                System.out.println("[ Menu Options ]");
                System.out.println("> 1. View All Transactions");
                System.out.println("> 2. Logout");
            } else {
                System.out.println("\n" + "Welcome, " + currentUser.getUsername() + "!");
                System.out.println("[ Menu Options ]");
                System.out.println("> 1. Deposit");
                System.out.println("> 2. Withdraw");
                System.out.println("> 3. History");
                System.out.println("> 4. Interest Calculator");
                System.out.println("> 5. Check Balance");
                System.out.println("> 6. Open Checking Account");
                System.out.println("> 7. Logout");
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
