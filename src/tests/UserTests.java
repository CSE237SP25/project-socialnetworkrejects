package tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import bankapp.User;

public class UserTests {

    @Test
    public void testCorrectPassword() {
        User user = new User("chad", "MyPass983");
        assertTrue(user.validatePassword("MyPass983"));
    }

    @Test
    public void testIncorrectPassword() {
        User user = new User("chad", "Cool1Pass!");
        assertFalse(user.validatePassword("WrongPass"));
    }

    @Test
    public void testEmptyPassword() {
        User user = new User("chad", "");
        assertTrue(user.validatePassword(""));
        assertFalse(user.validatePassword(" "));
    }

    @Test
    public void testCaseSensitivity() {
        User user = new User("chad", "SecretPass");
        assertFalse(user.validatePassword("secretpass"));
    }

    @Test
    public void testOpenCheckingAccount() {
        // Test opening a checking account
        User user = new User("3am", "now4am");
        user.openCheckingAccount();
        assertNotNull(user.getCheckingAccount());
    }
}