import java.util.Scanner;

import Algorithm.AES256;

public class Main {
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
        System.out.println("********************");
        System.out.println("*     EncriptIt    *");
        System.out.println("*    encript and   *");
        System.out.println("*   decrypt texts  *");
        System.out.println("*   with multiple  *");
        System.out.println("*    algoritmns    *");
        System.out.println("********************");
		do {	    
			String algorthim = setAlgorithm();
			switch (algorthim) {
				case "AES256": AES256.setOptionsMenu(); break;
			}
		    String option = setOption();
			
			String text= setText();
			
			System.out.println(execute(algorthim, option, text));
		
	        System.out.println("0) Continue");
	        System.out.println("1) Exit");
	        System.out.println("------------");
	    } while (!"1".equals( sc.nextLine()));
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

	public static String execute(String algorthim, String option, String rawText) {
		switch (algorthim) {
			case "AES256":
			    if(option.equals("encrypt")) {
				    String encryptedString = AES256.encrypt(rawText);
				    if (encryptedString == null) {
				        return "Encryption failed.";
				    }
					return encryptedString;
				}
				
				if(option.equals("decrypt")) {
				    String decryptedString = AES256.decrypt(rawText);
				    if (decryptedString == null) {
				        return "Dencryption failed.";
				    }
					return decryptedString;
				}
		}
		return "Unexpected Error";
	}
}
