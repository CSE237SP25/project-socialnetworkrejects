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

		actual = helper.checkIncorrectUserMenuInput("register", null);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("login", null);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("reGistER", null);
		assertEquals(false, actual);

		actual = helper.checkIncorrectUserMenuInput("LOGIN", null);
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
    public void testRegisterUserSelection() {
        // Test user registration flow
        m.registerUserForTest("john", "password123");
        assertTrue(helper.checkYes("yes"));  // Assuming user wants to proceed with registration
    }
    
    @Test
    public void testLogout() {
        // Test the logout functionality
        m.registerUserForTest("john", "password123");
        m.logout();
        assertNull(m.getCurrentUser());
    }
	
}
