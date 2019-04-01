package security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class TestPasswordHashing {
    @Test
    public void hashPasswordTest() {
        PasswordHashing ph = new PasswordHashing();
        // expected hash output from function when password = "123456"
        String expectedHash = "y+x6lOc+kwRcmZd1TSfRss4p1kItIwrfmXuUi2Hnjz2OBSf8AfRhmhQ/XqRO+WmQ5c6DKOPQeXhnIeC8zk6mPw==";
        // password to be used in hashPassword() function
        String password = "123456";
        // compares expected and actual hash
        assertEquals(expectedHash, ph.hashPassword(password));
        // tests deterministic nature of hash (produce same result every time)
        assertEquals(expectedHash, ph.hashPassword(password));
        // tests hashing entropy (small change produces vastly different hash)
        assertNotSame(expectedHash, ph.hashPassword(password));
    }

    @Test
    public void storeHashedPasswordTest(){
        PasswordHashing ph = new PasswordHashing();
        // shows that we aren't storing the plaintext password
        String password = "123456";
        String hash = ph.hashPassword(password);
        // password and hash should not be same (don't store user passwords)
        assertNotSame(password, hash);
    }
}
