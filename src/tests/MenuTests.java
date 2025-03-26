package tests;

import bankapp.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

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
        // No user logged in
        assertTrue(m.checkIncorrectUserMenuInput("wrong"));
        assertFalse(m.checkIncorrectUserMenuInput("register"));
        assertFalse(m.checkIncorrectUserMenuInput("login"));
    }

    @Test
    public void testCheckIncorrectUserBooleanInput() {
        assertTrue(m.checkIncorrectUserBooleanInput("wrong"));
        assertFalse(m.checkIncorrectUserBooleanInput("yes"));
        assertFalse(m.checkIncorrectUserBooleanInput("no"));
    }

    @Test
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
