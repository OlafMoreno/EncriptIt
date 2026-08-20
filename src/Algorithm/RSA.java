package Algorithm;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class RSA {

    private static final Scanner sc = new Scanner(System.in);

    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static String generateKeys() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);

            KeyPair keyPair = keyGen.generateKeyPair();

            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

            return ("Keys generated successfully.");

        } catch (Exception e) {
            //e.printStackTrace();
        	return e.getMessage();
        }
    }

    public static String encrypt(String strToEncrypt) {

        try {

            if (publicKey == null) {
                return null;
            }

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] encryptedBytes = cipher.doFinal(
                    strToEncrypt.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String strToDecrypt) {

        try {

            if (privateKey == null) {
                return null;
            }

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] decryptedBytes = cipher.doFinal(
                    Base64.getDecoder().decode(strToDecrypt));

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static void setOptionsMenu() {
        String option;
        do {

            do {
                System.out.println("------------");
                System.out.println("1) Show keys");
                System.out.println("2) Generate new keys");
                System.out.println("0) Continue");
                System.out.println("------------");

                option = sc.nextLine();

            } while (!"1".equals(option)
                    && !"2".equals(option)
                    && !"0".equals(option));

            switch (option) {

                case "1":

                    System.out.println("------------");
                    System.out.println("Public Key: ");
                    System.out.println(getPublicKey());
                    System.out.println();

                    System.out.println("Private Key: ");
                    System.out.println(getPrivateKey());
                    System.out.println("------------");

                    break;

                case "2":
                    generateKeys();
                    break;
            }

        } while (!"0".equals(option));
    }

    public static void setPublicKey(String key) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(spec);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public static void setPrivateKey(String key) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    
    public static String getPublicKey() {
        if (publicKey == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public static String getPrivateKey() {
        if (privateKey == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
}