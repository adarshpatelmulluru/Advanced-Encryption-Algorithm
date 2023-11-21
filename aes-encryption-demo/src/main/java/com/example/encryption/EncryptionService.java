package com.example.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptionService {

    private static final String encryptionKey = "AESencryptionKey";
    private static final String initializationVector = "1234567890123456";

    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException("Error during encryption: " + e.getMessage());
        }
    }

    public static String decrypt(String encryptedText) {
        try {
            // Remove unnecessary characters from the encrypted text
            encryptedText = encryptedText.replaceAll("[^a-zA-Z0-9+/=]", "");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(initializationVector.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Error during decryption: " + e.getMessage());
        }
    }
}
