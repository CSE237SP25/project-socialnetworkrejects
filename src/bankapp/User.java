package bankapp;

public class User {
    private String username;
    private String password;
    private SavingsAccount savingsAccount;
    private CheckingAccount checkingAccount; 

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.savingsAccount = new SavingsAccount(this);
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void openCheckingAccount() {
        if (this.checkingAccount != null) {
            throw new IllegalStateException("User already has a checking account.");
        }
        this.checkingAccount = new CheckingAccount(this);
    }
}
