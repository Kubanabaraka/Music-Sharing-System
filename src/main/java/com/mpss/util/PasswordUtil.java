package com.mpss.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * PasswordUtil - Utility class for password hashing using SHA-256.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class PasswordUtil {

    /**
     * Hashes a plaintext password using SHA-256.
     *
     * @param password the plaintext password
     * @return the hex-encoded SHA-256 hash
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    /**
     * Verifies a plaintext password against a hashed value.
     *
     * @param plaintext the plaintext password
     * @param hashed    the stored hash
     * @return true if the password matches
     */
    public static boolean verifyPassword(String plaintext, String hashed) {
        return hashPassword(plaintext).equals(hashed);
    }
}
