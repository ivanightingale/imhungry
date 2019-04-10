package security;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static security.PasswordHashing.hashPassword;


public class TestPasswordHashing{
    @Test
    public void hashPasswordTest() {
        // expected hash output from function when password = "123456"
        String expectedHash = "TmXu7dg2XWkhmDUbfbMykz6Khho34T3WLqauQl9PeYbK6fgUMYtWZJ6OgAtJMmkpdmX3/g8IqVqOYWdcwLrwog==";
        // password to be used in hashPassword() function
        String password = "123456";
        String salt = "randomsalt";
        // compares expected and actual hash
        assertEquals(expectedHash, PasswordHashing.hashPassword(password, salt));
    }

    @Test
    public void exceptionTest() throws NoSuchFieldException, IllegalAccessException{
        // expected hash output from function when password = "123456"
        String expectedHash = "TmXu7dg2XWkhmDUbfbMykz6Khho34T3WLqauQl9PeYbK6fgUMYtWZJ6OgAtJMmkpdmX3/g8IqVqOYWdcwLrwog==";
        // password to be used in hashPassword() function
        String password = "";
        String salt = "";
        // compares expected and actual hash
        assertEquals("", PasswordHashing.hashPassword(password, salt));
    }

    @Test
    public void saltGeneratorTest() {
        PasswordHashing ph = new PasswordHashing();
        String salt1 = PasswordHashing.getRandomSalt();
        String salt2 = PasswordHashing.getRandomSalt();

        assertNotEquals(salt1, salt2); //Show salts are random
        assertEquals(salt1.length(), 86); //Show salts are correct length
    }
}
