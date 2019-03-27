package security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;


public class TestPasswordHashing {
    @Test
    public void testHashPassword(){
        // expected hash output from function when password = "123456"
        String expectedHash = "";
        // password to be used in hashPassword() function
        String password = "123456";
        // compares expected and actual hash
        assertEquals(expectedHash, PasswordHashing.hashPassword(password));
        // tests deterministic nature of hash (produce same result every time)
        assertEquals(expectedHash, PasswordHashing.hashPassword(password));
        // tests hashing entropy (small change produces vastly different hash)
        assertNotSame(expectedHash, PasswordHashing.hashPassword(password));
    }

    @Test
    public void testGenerateSalt(){

    }

    @Test
    public void testStoreHashedPassword(){
        // shows that we aren't storing the plaintext password
        String password = "123456";
        String hash = PasswordHashing.hashPassword(password));
        // password and hash should not be same (don't store user passwords)
        assertNotSame(password, hash);
    }
}
