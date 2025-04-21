package bankapp;

import java.util.ArrayList;
import java.util.UUID;

public abstract class AbstractBankAccount {
    protected User user;
    protected double balance;
    protected double fraudThreshold = 10000;
    protected ArrayList<String> transactionHistory = new ArrayList<>();

    public AbstractBankAccount(User user) {
        this.user = user;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount invalid.");
        }
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("\nPotential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for deposit.");
        }
        this.balance += amount;
        transactionHistory.add(generateUserTransactionUUID() + " | Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            throw new IllegalArgumentException("Withdrawal amount invalid.");
        }
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("\nPotential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for withdrawal.");
        }
        this.balance -= amount;
        transactionHistory.add(generateUserTransactionUUID() + " | Withdrew: $" + amount);
    }

    public void transfer(double amount, AbstractBankAccount otherAccount) {
        if (amount < 0 || amount > this.balance) {
            throw new IllegalArgumentException("\nTransfer amount invalid.");
        }
        if (isFraudulentTransaction(amount)) {
            throw new IllegalArgumentException("\nPotential fraudulent transaction detected, please input less than $" 
                                               + this.fraudThreshold + " for transfer.");
        }
        otherAccount.deposit(amount);
        this.withdraw(amount);

        transactionHistory.add("Bank Notice: above transaction was part of a transfer of $" + amount + " to another account.");
        otherAccount.transactionHistory.add("Bank Notice: above transaction was part of a recieved transfer of $" + amount + " from another account.");
    }

    public double getCurrentBalance() {
        return this.balance;
    }

    public boolean isFraudulentTransaction(double amount) {
        return amount > this.fraudThreshold;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }

    public String generateTransactionUUID() {
        return UUID.randomUUID().toString();
    }

    public String generateUserTransactionUUID() {
        String userUsername = this.user.getUsername();
        String transactionUUID = this.generateTransactionUUID();
        return (userUsername + "-" + transactionUUID);
    }

    // Abstract method for interest, because checking account will not have it
    public abstract double calculateInterest(double years);
}
