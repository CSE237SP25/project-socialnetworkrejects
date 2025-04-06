package bankapp;
public class BankAccount {
    private double balance;

    private double fraudThreshold = 10000;
    
    public BankAccount() {
        this.balance = 0;  // New account opens with re$0
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

}