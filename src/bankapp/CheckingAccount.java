package bankapp;

public class CheckingAccount extends AbstractBankAccount {

    public CheckingAccount(User user) {
        super(user);
    }

    @Override
    public double calculateInterest(double years) {
        return 0; // 0% interest for a checking account
    }
}