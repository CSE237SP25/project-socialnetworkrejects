package bankapp;

public class User {
    private String username;
    private String password;
    private BankAccount account;

    /**
     * Creates a new User with a username, password, and a fresh BankAccount.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.account = new BankAccount();
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
    
    public BankAccount getAccount() {
        return account;
    }
}
