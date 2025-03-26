package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import bankapp.BankAccount;

public class BankAccountTests {

    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testSimpleDeposit() {
        account.deposit(25);
        assertEquals(25.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testNegativeDeposit() {
        try {
            account.deposit(-25);
            fail("Expected an IllegalArgumentException for negative deposit.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void testWithdrawal() {
        account.deposit(100);
        account.withdraw(50);
        assertEquals(50.0, account.getCurrentBalance(), 0.005);
    }

    @Test
    public void testInvalidWithdrawal() {
        account.deposit(50);
        try {
            account.withdraw(100);
            fail("Expected an IllegalArgumentException for withdrawing more than the balance.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
    }
}
