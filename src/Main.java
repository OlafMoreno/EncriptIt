import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Algorithm.AES256;
import Algorithm.RSA;

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
			case "RSA": RSA.setOptionsMenu(); break;
			}
		    String option = setOption();
			
			String text= null;
			do {
					text=getText();
			} while (text == null);
			System.out.println("------------");
			try {
				String newText = execute(algorthim, option, text);
				saveFile(newText);
			} catch (Exception e) {
				System.out.println("ERROR");
				System.out.println(e.getMessage());
			}
			System.out.println("------------");
	        System.out.println("0) Continue");
	        System.out.println("1) Exit");
	        System.out.println("------------");
	    } while (!"1".equals( sc.nextLine()));
	}
	
	public static String setOption() {
		String option;

	    do {
	        System.out.println("------------");
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
	        System.out.println("------------");
	        System.out.println("Algorithms:");
	        System.out.println("1) AES256");
	        System.out.println("2) RSA");
	        System.out.println("------------");
	        option = sc.nextLine();
	    } while (!"1".equals(option) && !"2".equals(option));

	    switch (option) {
	        case "1":
	            option = "AES256";
	            break;
	        case "2":
	            option = "RSA";
	            break;
	    }
		return option;
		
	}
	
	public static String getText(){
		String option;

	    do {
	        System.out.println("------------");
	        System.out.println("1) Write It");
	        System.out.println("2) Select File");
	        System.out.println("------------");
	        option = sc.nextLine();
	    } while (!"1".equals(option) && !"2".equals(option));
	    String output = null;
	    switch (option) {
	        case "1":
	            output=setText();
	            break;
	        case "2":
			try {
				output=readFile();
			} catch (Exception e) {
				output=null;
		        System.out.println("------------");
		        System.out.println(e.getMessage());
		        System.out.println("------------");
			}
	            break;
	    }
		return output;
		
	}
	
	public static String setText() {
        System.out.println("------------");
		System.out.println("Enter text:");
        System.out.println("------------");
		String str= sc.nextLine();
		return str;
	}

	public static String execute(String algorthim, String option, String rawText) throws Exception {
		switch (algorthim) {
			case "AES256":
			    if(option.equals("encrypt")) {
				    String encryptedString = AES256.encrypt(rawText);
				    if (encryptedString == null) {
						throw new Exception("Encryption failed");
				    }
					return encryptedString;
				}
				
				if(option.equals("decrypt")) {
				    String decryptedString = AES256.decrypt(rawText);
				    if (decryptedString == null) {
						throw new Exception("Decryption failed");
				    }
					return decryptedString;
				}
			case "RSA":
			    if(option.equals("encrypt")) {
				    String encryptedString = RSA.encrypt(rawText);
				    if (encryptedString == null) {
						throw new Exception("Encryption failed");
				    }
					return encryptedString;
				}
				
				if(option.equals("decrypt")) {
				    String decryptedString = RSA.decrypt(rawText);
				    if (decryptedString == null) {
						throw new Exception("Decryption failed");
				    }
					return decryptedString;
				}
		}
		throw new Exception("Unexpected Error");
	}
	public static void saveFile(String newText) {
	    System.out.println("Enter NameFile:");
	    System.out.println("------------");

	    String str = sc.nextLine();

	    Path folder = Path.of("src/data/output");
	    Path file = folder.resolve(str + ".txt");

	    int number = 1;

	    while (Files.exists(file)) {
	        file = folder.resolve(str + number + ".txt");
	        number++;
	    }

	    try {
	        Files.createDirectories(folder);
	        Files.writeString(file, newText);
		    System.out.println("------------");
	        System.out.println("File saved successfully as " + file.getFileName());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    public static String readFile() throws Exception {

        Path folder = Path.of("src/data/input");
		Files.createDirectories(folder);

        List<Path> files = new ArrayList<>();

        int i = 1;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            System.out.println("------------");
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    files.add(file);
                    System.out.println(i + "- " + file.getFileName());
                    i++;
                }
            }
        }

        if (files.isEmpty()) {
    		throw new Exception("Empty folder, put the archive on src/data/input");
        }

        int opcion;
        do {
            System.out.println("Select a file: ");
            System.out.println("------------");
            opcion = Integer.parseInt(sc.nextLine());
        } while (opcion < 1 || opcion > files.size());

        Path selectedFile = files.get(opcion - 1);

        String text = Files.readString(selectedFile);

        return text;
    }
}
