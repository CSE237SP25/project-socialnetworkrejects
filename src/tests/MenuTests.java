package tests;

import bankapp.Menu;
import bankapp.MenuInputHelper;
import bankapp.User;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MenuTests {

    private Menu m;
    private MenuInputHelper helper;
    private User u;

    @Before
    public void setUp() {
        m = new Menu();
        helper = new MenuInputHelper();
        u = new User("testUser", "testPassword"); 
    }
    
    @Test
    public void testCheckYes() {
        assertTrue(helper.checkYes("yes"));
        assertFalse(helper.checkYes("no"));
    }

    @Test
    public void testCheckNo() {
        assertTrue(helper.checkNo("no"));
        assertFalse(helper.checkNo("yes"));
    }

	@Test
	public void testCheckIncorrectUserMenuInput() {
		boolean actual = helper.checkIncorrectUserMenuInput("this is clearly wrong", u);
		assertEquals(true, actual);

		actual = helper.checkIncorrectUserMenuInput("register", u);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("login", u);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("reGistER", u);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("LOGIN", u);
		assertEquals(false, actual);
	}

	@Test
	public void testCheckIncorrectUserBooleanInput() {
		boolean actual = helper.checkIncorrectUserBooleanInput("this is clearly wrong");
		assertEquals(true, actual);

		actual = helper.checkIncorrectUserBooleanInput("yes");
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserBooleanInput("no");
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserBooleanInput("yES");
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserBooleanInput("NO");
		assertEquals(false, actual);
	}
	
	@Test
    public void testRegisterUser() {
        // Test user registration flow
        m.registerUserForTest("john", "password123");
        assertTrue(helper.checkYes("yes"));  // Assuming user wants to proceed with registration
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
