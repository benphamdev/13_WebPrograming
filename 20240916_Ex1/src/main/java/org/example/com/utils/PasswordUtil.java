package org.example.com.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}