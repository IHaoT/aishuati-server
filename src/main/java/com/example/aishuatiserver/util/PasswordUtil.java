package com.example.aishuatiserver.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {
    public static String encrypt(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Base64.Encoder base64Encoder = Base64.getEncoder();
            return base64Encoder.encodeToString(md5.digest(password.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
    public static boolean check(String password, String userInput) {
        return password.equals(encrypt(userInput));
    }

    public static String generateToken() {
        return generateToken(null);
    }

    public static String generateToken(String seed) {
        String s = ((Long)System.currentTimeMillis()).toString();
        if (seed != null) {
            s += seed;
        } else {
            s += "Keadin & Sumo // Token String";
        }
        return encrypt(s);
    }

    public static boolean checkToken(String token, String userInput) {
        long now = System.currentTimeMillis()/1000/60/5;
        String s1 = Long.toString(now);
        String s2 = Long.toString(now-1);
        s1 += token;
        s2 += token;
        return userInput.equals(encrypt(s1)) || userInput.equals(encrypt(s2));
    }

    public static String generatePassword(String username, String password) {
        return encrypt(username+password);
    }
}

