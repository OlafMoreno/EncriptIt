import java.util.Scanner;

import Algorithm.AES256;

public class Main {
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
			    
		String algorthim = setAlgorithm();
		switch (algorthim) {
			case "AES256": AES256.setOptionsMenu(); break;
		}
	    String option = setOption();
		
		String str= setText();
		
		switch (algorthim) {
			case "AES256":
			    if(option.equals("encrypt")) {
				    String encryptedString = AES256.encrypt(str);
				    if (encryptedString == null) {
				        System.err.println("Encryption failed.");
				        return;
				    }
					System.out.println(encryptedString);
					return ;
				}
				
				if(option.equals("decrypt")) {
				    String decryptedString = AES256.decrypt(str);
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
	        System.out.println("------------");
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
	
	public static String setAlgorithm() {
		String option;

	    do {
	        System.out.println("1) AES256");
	        System.out.println("------------");
	        option = sc.nextLine();
	    } while (!"1".equals(option));

	    switch (option) {
	        case "1":
	            option = "AES256";
	            break;
	    }
		return option;
		
	}
	
	public static String setText() {
		System.out.println("Enter text:");
        System.out.println("------------");
		String str= sc.nextLine();
		return str;
	}

}
