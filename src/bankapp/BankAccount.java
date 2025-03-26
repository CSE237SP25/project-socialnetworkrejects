package bankapp;
public class BankAccount {
    private double balance;
    
    public BankAccount() {
        this.balance = 0;  // New account opens with $0
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
        this.balance += amount;
    }
    /**
     * Withdraw the given amount from the account
     * @param amount can't be negative & not greater than the current balance
     */
    public void withdraw(double amount) {
        if (amount < 0 || amount > this.balance) {
            throw new IllegalArgumentException("Withdrawal amount invalid.");
        }
        this.balance -= amount;
    }
    /**
     * Returns the current account balance
     */
    public double getCurrentBalance() {
        return this.balance;
    }
}