package com.example.examsystem.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    public static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] bytes = md.digest();
            int digit;
            StringBuilder sb = new StringBuilder("");
            for (byte aByte : bytes) {
                digit = aByte;
                if (digit < 0)
                    digit += 256;
                else if (digit < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(digit));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
