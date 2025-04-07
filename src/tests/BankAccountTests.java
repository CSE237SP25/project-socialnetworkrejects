package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import bankapp.BankAccount;
import bankapp.User;

public class BankAccountTests {

	private BankAccount account;
	private User user;

	private BankAccount otherAccount;
	private User otherUser;

    @Before
    public void setUp() {
		this.user = new User("testUser", "testPassword");
        this.account = new BankAccount(user);

		this.otherUser = new User("otherUser", "otherPassword");
		this.otherAccount = new BankAccount(otherUser);
    }

	@Test
	public void testSimpleDeposit() {
		//Call the method being tested
		account.deposit(25);
		
		//Use assertions to verify results
		assertEquals(account.getCurrentBalance(), 25.0, 0.005);
	}
	
	@Test
	public void testNegativeDeposit() {
		try {
			account.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	public void testSimpleWithdraw() {
		// test values
		account.deposit(100);
		account.withdraw(25);
		assertEquals(75.00, account.getCurrentBalance(), 0.005);
	}

	@Test
	public void testWithdrawWithInsufficientFunds() {
		account.deposit(50);
		try {
			account.withdraw(200);
			fail("Expected an IllegalArgumentException, because a withdraw was tried with insufficient funds.");
		} catch (IllegalArgumentException e) {
			assertTrue(e != null);
		}
	}

	@Test
	public void testWithdrawWithNegativeFunds() {
        account.deposit(50);
        try {
            account.withdraw(-50);
            fail("Expected an IllegalArgumentException, because a withdraw was tried with negative funds.");
        } catch (IllegalArgumentException e) {
            assertTrue(e != null);
        }
	}
    
    @Test
    public void testSimpleTransfer() {
    	account.deposit(50);
    	account.transfer(25.0, otherAccount);
    	assertEquals(25.0,otherAccount.getCurrentBalance(),0.005);
    	
    }

	@Test
    public void testTransferWithInsufficientFunds() {
    	this.account.deposit(50);
    	
    	try {
    		account.transfer(200, otherAccount);
    		fail("Expected an IllegalArgumentException, because a transfer was tried with insufficient funds.");
    	}
    	catch (IllegalArgumentException e) {
    		assertTrue(e != null);
    	}
    	
    }
    
    @Test
    public void testTransferWithNegativeFunds() {
    	this.account.deposit(50);
    	
    	try {
    		account.transfer(-25.0, otherAccount);
    		fail("Expected an IllegalArgumentException, because a transfer was tried with negative funds.");
    	}
    	catch (IllegalArgumentException e) {
    		assertTrue(e != null);
    	}
    	
    }
    
}
