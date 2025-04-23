package bankapp;

public class SavingsAccount extends AbstractBankAccount {
    private double interestRate = 0.05;

    public SavingsAccount(User user) {
        super(user);
    }

    @Override
    public double calculateInterest(double years) {
        if (years < 0) {
            throw new IllegalArgumentException("Years cannot be negative.");
        }
        return this.balance * this.interestRate * years;
    }
}