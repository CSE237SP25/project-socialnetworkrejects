package bankapp;

public class CheckingAccount extends AbstractBankAccount {

    public CheckingAccount(User user) {
        super(user);
    }

    @Override
    public double calculateInterest(double years) {
        return 0; // No interest for checking accounts
    }
}
