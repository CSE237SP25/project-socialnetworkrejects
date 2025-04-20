package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import bankapp.SavingsAccount;
import bankapp.CheckingAccount;
import bankapp.User;

public class BankAccountTests {
	//testing

    private SavingsAccount savingsAccount;
    private User user;

    private CheckingAccount checkingAccount;
    private User otherUser;

    @Before
    public void setUp() {
        this.user = new User("testUser", "testPassword");
        this.savingsAccount = new SavingsAccount(user);
        
        this.otherUser = new User("otherUser", "otherPassword");
        this.checkingAccount = new CheckingAccount(otherUser);
    }

    @Test
    public void testSimpleDeposit() {
        savingsAccount.deposit(25);
        assertEquals(25.0, savingsAccount.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeDeposit() {
        try {
            savingsAccount.deposit(-25);
            fail("Expected IllegalArgumentException due to negative deposit.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testSimpleWithdraw() {
        savingsAccount.deposit(100);
        savingsAccount.withdraw(25);
        assertEquals(75.00, savingsAccount.getCurrentBalance(), 0.005);
    }

    @Test
    public void testWithdrawWithInsufficientFunds() {
        savingsAccount.deposit(50);
        try {
            savingsAccount.withdraw(200);
            fail("Expected an IllegalArgumentException, because a withdrawal was tried with insufficient funds.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testWithdrawWithNegativeFunds() {
        savingsAccount.deposit(50);
        try {
            savingsAccount.withdraw(-50);
            fail("Expected an IllegalArgumentException, because a withdrawal was tried with negative funds.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testSimpleTransfer() {
        savingsAccount.deposit(50);
        savingsAccount.transfer(25.0, checkingAccount);
        assertEquals(25.0, checkingAccount.getCurrentBalance(), 0.005);
    }

    @Test
    public void testTransferWithInsufficientFunds() {
        savingsAccount.deposit(50);
        try {
            savingsAccount.transfer(200, checkingAccount);
            fail("Expected an IllegalArgumentException, because a transfer was tried with insufficient funds.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testTransferWithNegativeFunds() {
        savingsAccount.deposit(50);
        try {
            savingsAccount.transfer(-25.0, checkingAccount);
            fail("Expected an IllegalArgumentException, because a transfer was tried with negative funds.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testInterestCalculator() {
        savingsAccount.deposit(100);
        double interest = savingsAccount.calculateInterest(1);
        assertEquals(5, interest, 0.005);
    }

    @Test
    public void testUserTransactionUUIDLength() {
        String userTransactionUUID = user.getSavingsAccount().generateUserTransactionUUID();
        String username = user.getUsername();
        int usernameLength = username.length();

        assertEquals(usernameLength + 37, userTransactionUUID.length());

    }
    
    @Test
    public void testCheckingAccountInterestIsZero() {
        double interest = checkingAccount.calculateInterest(5);
        assertEquals(0.0, interest, 0.001);
    }

}
