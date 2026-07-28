import java.util.Scanner;

import Algorithm.AES256;

public class Main {
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
			    
	    String option = setOption();
		
		String str= setText();
		
		String algorthim = "AES256";
		switch (algorthim) {
			case "AES256":
				String secretKey = "key";
			    String salt = "salt";
			    if(option.equals("encrypt")) {
				    String encryptedString = AES256.encrypt(str, secretKey, salt);
				    if (encryptedString == null) {
				        System.err.println("Encryption failed.");
				        return;
				    }
					System.out.println(encryptedString);
					return ;
				}
				
				if(option.equals("decrypt")) {
				    String decryptedString = AES256.decrypt(str, secretKey, salt);
				    if (decryptedString == null) {
				        System.err.println("Dencryption failed.");
				        return;
				    }
					System.out.println(decryptedString);
					return ;
				}
		}
	}
	
	public static String setOption() {
		String option;

	    do {
	        System.out.println("1) Encrypt");
	        System.out.println("2) Decrypt");
	        option = sc.nextLine();
	    } while (!"1".equals(option) && !"2".equals(option));

	    switch (option) {
	        case "1":
	            option = "encrypt";
	            break;
	        case "2":
	            option = "decrypt";
	            break;
	    }
		return option;
		
	}
	
	public static String setText() {
		System.out.println("Enter text:");
		String str= sc.nextLine();
		return str;
	}

}
