package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

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
    
    @Test
    public void testTransfer() {
    	this.account.deposit(50);
    	BankAccount otherBankAccount = new BankAccount();
    	account.transfer(25.0, otherBankAccount);
    	assertEquals(25.0,otherBankAccount.getCurrentBalance(),0.005);
    	
    }
    
    @Test
    public void testInvalidTransfer() {
    	this.account.deposit(50);
    	BankAccount otherBankAccount = new BankAccount();
    	
    	try {
    		account.transfer(-25.0, otherBankAccount);
    		fail("Expected an IllegalArgumentException for transfering negative amounts of money.");
    	}
    	catch (IllegalArgumentException e) {
    		assertTrue(e != null);
    	}
    	
    }
    
}
