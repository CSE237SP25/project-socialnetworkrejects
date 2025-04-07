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
}
