package tests;

import bankapp.SavingsAccount;
import bankapp.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SavingsAccountTests {

    private SavingsAccount savings;
    private User user;

    @Before
    public void setUp() {
        user = new User("alexander", "password");
        savings = new SavingsAccount(user);
    }

    @Test
    public void testDepositIncreasesBalance() {
        savings.deposit(200);
        assertEquals(200.0, savings.getCurrentBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeThrows() {
        savings.deposit(-1);
    }

    @Test
    public void testWithdrawDecreasesBalance() {
        savings.deposit(100);
        savings.withdraw(40);
        assertEquals(60.0, savings.getCurrentBalance(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawTooMuchThrows() {
        savings.deposit(50);
        savings.withdraw(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNegativeThrows() {
        savings.withdraw(-10);
    }

    @Test
    public void testTransactionHistorySize() {
        savings.deposit(100);
        savings.withdraw(20);
        assertEquals(2, savings.getTransactionHistory().size());
    }

    @Test
    public void testTransactionUUIDIncludesUsername() {
        String uuid = savings.generateUserTransactionUUID();
        assertTrue(uuid.startsWith(user.getUsername() + "-"));
        assertEquals(user.getUsername().length() + 37, uuid.length());
    }

    @Test
    public void testInterestCalculationCorrectness() {
        savings.deposit(1000);
        double interest = savings.calculateInterest(1);
        assertEquals(50.0, interest, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInterestNegativeYearsThrows() {
        savings.calculateInterest(-1);
    }

    @Test
    public void testSetFraudThresholdBlocksLargeDeposit() {
        savings.setFraudThreshold(100);
        try {
            savings.deposit(200);
            fail("Expected exception for fraudulent deposit");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().toLowerCase().contains("fraudulent"));
        }
    }

    @Test
    public void testSetFraudThresholdAllowsSmallerDeposit() {
        savings.setFraudThreshold(500);
        savings.deposit(200);
        assertEquals(200.0, savings.getCurrentBalance(), 0.001);
    }
}
