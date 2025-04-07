package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

public class BankAccountTests {

	@Test
	public void testSimpleDeposit() {
		//1. Create objects to be tested
		BankAccount account = new BankAccount();
		
		//2. Call the method being tested
		account.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		//1. Create object to be tested
		BankAccount account = new BankAccount();

		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	public void testWithdraw() {
		// test values
		m.registerUserForTest("testuser", "testpass");
		m.deposit(100);
		m.withdraw(25);
		assertEquals(75.00, m.getCurrentBalance(), 0.005);
	}

	@Test
	public void testWithdrawInsufficientFunds() {
		m.registerUserForTest("testuser", "testpass");
		m.deposit(50);
		try {
			m.withdraw(200);
			fail("Expected an IllegalArgumentException for insufficient funds.");
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	@Test
	public void testWithdrawNegativeFunds() {
		m.registerUserForTest("testuser", "testpass");
		try {
			m.withdraw(-25);
			fail("Expected an IllegalArgumentException for a negative withdrawal.");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

}
