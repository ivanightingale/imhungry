package security;

import java.security.SecureRandom;

public class PasswordHashing {
    private static final SecureRandom RAND = new SecureRandom();
    private static String salt;

    public PasswordHashing(){
        generateSalt();
    }

    public static String hashPassword(String password){
        return "";
    }

    public static String generateSalt(){
        return "";
    }
}
