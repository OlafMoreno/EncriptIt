package Algorithm;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

public class AES256  {
	private static final int KEY_LENGTH = 256;
	private static final int ITERATION_COUNT = 65536;
	private static final Scanner sc = new Scanner(System.in);

	static String salt = "salt";
	static String secretKey = "key";
	
	public static String encrypt(String strToEncrypt) throws Exception {

	    try {

	        SecureRandom secureRandom = new SecureRandom();
	        byte[] iv = new byte[16];
	        secureRandom.nextBytes(iv);
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(getSecretKey().toCharArray(), getSalt().getBytes(), ITERATION_COUNT, KEY_LENGTH);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);

	        byte[] cipherText = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
	        byte[] encryptedData = new byte[iv.length + cipherText.length];
	        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
	        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

	        return Base64.getEncoder().encodeToString(encryptedData);
	    } catch (Exception e) {
	    	throw e;
	    }
	  }
	
	public static String decrypt(String strToDecrypt) throws Exception {

	    try {

	        byte[] encryptedData = Base64.getDecoder().decode(strToDecrypt);
	        byte[] iv = new byte[16];
	        System.arraycopy(encryptedData, 0, iv, 0, iv.length);
	        IvParameterSpec ivspec = new IvParameterSpec(iv);

	        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	        KeySpec spec = new PBEKeySpec(getSecretKey().toCharArray(), getSalt().getBytes(), ITERATION_COUNT, KEY_LENGTH);
	        SecretKey tmp = factory.generateSecret(spec);
	        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);

	        byte[] cipherText = new byte[encryptedData.length - 16];
	        System.arraycopy(encryptedData, 16, cipherText, 0, cipherText.length);

	        byte[] decryptedText = cipher.doFinal(cipherText);
	        return new String(decryptedText, "UTF-8");
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	
	public static void setOptionsMenu() {
		String option;
		do {
		    do {
		        System.out.println("------------");
		        System.out.println("1) Show variables");
		        System.out.println("2) Write Salt");
		        System.out.println("3) Write Secret Key");
		        System.out.println("0) Continue");
		        System.out.println("------------");
		        option = sc.nextLine();
		    } while (!"1".equals(option) && !"2".equals(option) && !"3".equals(option) && !"0".equals(option));
	
		    switch (option) {
		        case "1":
			        System.out.println("------------");
			        System.out.println("Salt: "+getSalt());
			        System.out.println("Secret Key: "+getSecretKey());
			        System.out.println("------------");
		            break;
		        case "2":
			        System.out.println("------------");
			        System.out.println("New Salt: ");
			        System.out.println("------------");
			        String newSalt = sc.nextLine();
			        setSalt(newSalt);
		            break;
		        case "3":
			        System.out.println("------------");
			        System.out.println("New Secret Key: ");
			        System.out.println("------------");
			        String newSecretKey = sc.nextLine();
			        setSecretKey(newSecretKey);
		            break;
		    }
		}while(!"0".equals(option));		
	}
	
	public static void setSalt(String newSalt) {
		salt= newSalt;
	}
	
	public static String getSalt() {
		return salt;
	}
	
	public static void setSecretKey(String newSecretKey) {
		secretKey= newSecretKey;
	}
	
	public static String getSecretKey() {
		return secretKey;
	}
}
