package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public final class PasswordEncryption {

    private static final String ALGORITHM = "AES";

    public static String encrypt(String password) {
        KeyGenerator keyGen;
        SecretKey secretKey;
        Cipher cipher;
        byte[] encryptedPasswordBytes;
        try {
                keyGen = KeyGenerator.getInstance(ALGORITHM);
                keyGen.init(128);
                secretKey = keyGen.generateKey();
                cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                encryptedPasswordBytes = cipher.doFinal(password.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(encryptedPasswordBytes);
    }
}
