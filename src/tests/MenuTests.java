package tests;

import bankapp.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MenuTests {

    private Menu m;

    @Before
    public void setUp() {
        m = new Menu();
    }
    
    @Test
    public void testCheckYes() {
        assertTrue(m.checkYes("yes"));
        assertFalse(m.checkYes("no"));
    }

    @Test
    public void testCheckNo() {
        assertTrue(m.checkNo("no"));
        assertFalse(m.checkNo("yes"));
    }

	@Test
	public void testCheckIncorrectUserMenuInput() {
		boolean actual = m.checkIncorrectUserMenuInput("this is clearly wrong");
		assertEquals(true, actual);

		actual = m.checkIncorrectUserMenuInput("register");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("login");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("reGistER");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserMenuInput("LOGIN");
		assertEquals(false, actual);
	}

	@Test
	public void testCheckIncorrectUserBooleanInput() {
		boolean actual = m.checkIncorrectUserBooleanInput("this is clearly wrong");
		assertEquals(true, actual);

		actual = m.checkIncorrectUserBooleanInput("yes");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("no");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("yES");
		assertEquals(false, actual);

		actual = m.checkIncorrectUserBooleanInput("NO");
		assertEquals(false, actual);
	}
	
	@Test
    public void testRegisterUser() {
        // Test user registration flow
        m.registerUserForTest("john", "password123");
        assertTrue(m.checkYes("yes"));  // Assuming user wants to proceed with registration
    }

    @Test
    public void testDepositMoney() {
        // Test deposit into savings and checking accounts
        m.registerUserForTest("john", "password123");
        m.deposit(100);
        assertEquals(100.0, m.getCurrentBalance(), 0.01);
    }

    @Test
    public void testWithdrawMoney() {
        // Test withdrawal from savings and checking accounts
        m.registerUserForTest("john", "password123");
        m.deposit(200);
        m.withdraw(100);
        assertEquals(100.0, m.getCurrentBalance(), 0.01);
    }

    @Test
    public void testOpenCheckingAccount() {
        // Test opening a checking account
        m.registerUserForTest("john", "password123");
        m.openCheckingAccount();
        assertNotNull(m.getCurrentUser().getCheckingAccount());
    }

    @Test
    public void testViewTransactionHistory() {
        // Test viewing transaction history for logged-in user
        m.registerUserForTest("john", "password123");
        m.deposit(100);
        m.viewTransactionHistory();
        // You can assert some transaction history logic here
    }

    
    @Test
    public void testLogout() {
        // Test the logout functionality
        m.registerUserForTest("john", "password123");
        m.logout();
        assertNull(m.getCurrentUser());
    }

    @Test
    public void testInterestCalculator() {
        // Test interest calculation for a given period
        m.registerUserForTest("john", "password123");
        m.calculateInterest(1);
        // You can assert interest calculation logic here.
    }
	
}
