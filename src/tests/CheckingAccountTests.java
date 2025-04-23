package tests;

import bankapp.CheckingAccount;
import bankapp.SavingsAccount;
import bankapp.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckingAccountTests {

    private CheckingAccount checking;
    private User user;

    @Before
    public void setUp() {
        user = new User("alexander", "password");
        checking = new CheckingAccount(user);
    }

    @Test
    public void testDepositIncreasesBalance() {
        checking.deposit(200);
        assertEquals(200.0, checking.getCurrentBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeThrows() {
        checking.deposit(-1);
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        checking.deposit(100);
        checking.withdraw(40);
        assertEquals(60.0, checking.getCurrentBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawTooMuchThrows() {
        checking.deposit(50);
        checking.withdraw(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNegativeThrows() {
        checking.withdraw(-10);
    }

    @Test
    public void testTransactionHistorySize() {
        checking.deposit(100);
        checking.withdraw(20);
        assertEquals(2, checking.getTransactionHistory().size());
    }

    @Test
    public void testTransactionUUIDIncludesUsername() {
        String uuid = checking.generateUserTransactionUUID();
        assertTrue(uuid.startsWith(user.getUsername() + "-"));
        assertEquals(user.getUsername().length() + 37, uuid.length());
    }

    @Test
    public void testSetFraudThresholdBlocksLargeDeposit() {
        checking.setFraudThreshold(100);
        try {
            checking.deposit(200);
            fail("Expected exception for fraudulent deposit");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("fraudulent"));
        }
    }

    @Test
    public void testSetFraudThresholdAllowsSmallerDeposit() {
        checking.setFraudThreshold(500);
        checking.deposit(200);
        assertEquals(200.0, checking.getCurrentBalance(), 0.001);
    }
}

