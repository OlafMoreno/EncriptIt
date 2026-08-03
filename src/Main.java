import java.io.BufferedReader;
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
				System.out.println("New text:");
				System.out.println(newText);
			} catch (Exception e) {
				System.out.println("ERROR");
				System.out.println(e.getMessage());
			}
			System.out.println("------------");

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
	
    public static String readFile() throws Exception {

        Path carpeta = Path.of("src/data");

        List<Path> archivos = new ArrayList<>();

        int i = 1;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(carpeta)) {
            for (Path archivo : stream) {
                if (Files.isRegularFile(archivo)) {
                    archivos.add(archivo);
                    System.out.println(i + "- " + archivo.getFileName());
                    i++;
                }
            }
        }

        if (archivos.isEmpty()) {
    		throw new Exception("Empty folder, put the archive on src/data");
        }

        int opcion;
        do {
            System.out.print("Select a file: ");
            opcion = Integer.parseInt(sc.nextLine());
        } while (opcion < 1 || opcion > archivos.size());

        Path archivoSeleccionado = archivos.get(opcion - 1);

        String contenido = Files.readString(archivoSeleccionado);

        return contenido;
    }
}
