package bankapp;

import java.util.ArrayList;
import java.util.UUID;

public class BankAccount {
    private User user; // Reference to the User who owns this account

    private double balance;

    private double fraudThreshold = 10000;
    private double interestRate = 0.05; // 5% interest rate
    private ArrayList<String> transactionHistory = new ArrayList<>();

    
    public BankAccount(User user) {
        this.balance = 0;  // New account opens with re$0
        this.user = user;
        //Potential New Idea: Add account minimum like real bank accounts
    }
    /**
     * Deposits the given amount into the account
     * @param amount can't be negative
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount invalid.");
        }
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("Potential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for deposit.");
        }

        this.balance += amount;
        transactionHistory.add(generateUserTransactionUUID() + " Deposited: $" + amount);
    }
    /**
     * Withdraw the given amount from the account
     * @param amount can't be negative & not greater than the current balance
     */
    public void withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            throw new IllegalArgumentException("Withdrawal amount invalid.");
        }
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("Potential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for withdrawal.");
        }
        this.balance -= amount;
        transactionHistory.add("Withdrew: $" + amount);
    }
    
    public void transfer(double amount, BankAccount otherAccount) {
    	if (amount < 0 || amount > this.balance) {
    		throw new IllegalArgumentException("Transfer amount invalid");
    	}
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("Potential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for transfer.");
        }
    	otherAccount.deposit(amount);
    	this.withdraw(amount);
    	transactionHistory.add("Transferred $" + amount + " to another account.");

    }
    /**
     * Returns the current account balance
     */
    public double getCurrentBalance() {
        return this.balance;
    }

    public boolean isFraudulentTransaction(double amount) {
        //Change the value associated with fraudThreshold as needed for fraud detection
        return amount > this.fraudThreshold;
    }
    
    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }


    public double calculateInterest(double years) {
        if (years < 0) {
            throw new IllegalArgumentException("Years cannot be negative.");
        }
        // Calculate interest based on the current balance and interest rate
        double interest = this.balance * this.interestRate * years;
        return interest;
    }

    public String generateTransactionUUID() {
        //generates a unique key for each transaction
        return UUID.randomUUID().toString();
    }

    /*
    Generates a unique transaction UUID for the user by combining the username and 
    the account's transaction UUID (36 characters, not including the dash)
    Example: "johnDoe-123e4567-e89b-12d3-a456-426614174000"
    */
    public String generateUserTransactionUUID() {
        String userUsername = this.user.getUsername();
        String transactionUUID = this.generateTransactionUUID();
        return (userUsername + "-" + transactionUUID);
    }

}