package org.com.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil_22162006 {
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String password, String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}