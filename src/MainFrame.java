import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import Algorithm.AES256;
import Algorithm.RSA;

import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class MainFrame {

    private JFrame mainFrame;

    private int windowWidth=500;
    private int windowHeight=550;
    private int columnWidth = 387;
    private int columnHeight = 50;

    private JRadioButton aesButton;
    private JRadioButton rsaButton;
    private JRadioButton encryptButton;
    private JRadioButton decryptButton;
    private JTextField saltText;
    private JTextField secretKeyText;
    private JTextField publicKeyText;
    private JTextField privateKeyText;

    
    private String phase = "alghoritmSelector"; 
    private String algorithm = ""; 
    private String action = null;
    private String text = null;
    
    private JLabel actionLabel;
    private JButton backButton;
    private JButton nextButton;
    private JEditorPane editorPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame window = new MainFrame();
                    window.mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        createFrame();
        createAlgorithmSelector();
        createActionPanel();
    }

    private void createFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Encript It");
        mainFrame.setBounds(100, 100, windowWidth, windowHeight);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBorder(
            new EmptyBorder(15, 15, 15, 15)
        );
        mainFrame.setContentPane(mainPanel);
    }

    private void createTitle(String text) {
        Container contentPane = mainFrame.getContentPane();
        BorderLayout layout = (BorderLayout) contentPane.getLayout();
        Component currentPanel = layout.getLayoutComponent(BorderLayout.NORTH);
        if (currentPanel != null) {
            contentPane.remove(currentPanel);
        }
        contentPane.revalidate();
        contentPane.repaint();
        
        JLabel title = new JLabel(text);
        mainFrame.getContentPane().add(title, BorderLayout.NORTH);
    }

    private void createAlgorithmSelector() {
    	createTitle("Select a algorithm (Phase 1/5)");
        JPanel algorithmSelector = new JPanel();
        algorithmSelector.setBorder(
                new EmptyBorder(20, 20, 20, 20)
            );
        mainFrame.getContentPane().add(
            algorithmSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_algorithmSelector = new GridBagLayout();
        gbl_algorithmSelector.columnWidths = new int[]{columnWidth};
        gbl_algorithmSelector.rowHeights = new int[]{columnHeight, columnHeight, columnHeight, 0};
        gbl_algorithmSelector.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_algorithmSelector.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        algorithmSelector.setLayout(gbl_algorithmSelector);
        aesButton = new JRadioButton("AES256");
        GridBagConstraints gbc_aesButton = new GridBagConstraints();
        gbc_aesButton.fill = GridBagConstraints.BOTH;
        gbc_aesButton.insets = new Insets(0, 0, 5, 0);
        gbc_aesButton.gridx = 0;
        gbc_aesButton.gridy = 0;
        algorithmSelector.add(aesButton, gbc_aesButton);
        ButtonGroup group = new ButtonGroup();
        group.add(aesButton);
        rsaButton = new JRadioButton("RSA");
        GridBagConstraints gbc_rsaButton = new GridBagConstraints();
        gbc_rsaButton.fill = GridBagConstraints.BOTH;
        gbc_rsaButton.gridx = 0;
        gbc_rsaButton.gridy = 1;
        algorithmSelector.add(rsaButton, gbc_rsaButton);
        group.add(rsaButton);

    }
    
    private void createActionSelector() {
    	createTitle("Select a action (Phase 3/5)");
        JPanel optionSelector = new JPanel();
        optionSelector.setBorder(
                new EmptyBorder(20, 20, 20, 20)
            );
        mainFrame.getContentPane().add(
        		optionSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_algorithmSelector = new GridBagLayout();
        gbl_algorithmSelector.columnWidths = new int[]{columnWidth};
        gbl_algorithmSelector.rowHeights = new int[]{columnHeight, columnHeight, columnHeight, 0};
        gbl_algorithmSelector.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_algorithmSelector.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        optionSelector.setLayout(gbl_algorithmSelector);
        encryptButton = new JRadioButton("Encrypt");
        GridBagConstraints gbc_encryptButton = new GridBagConstraints();
        gbc_encryptButton.fill = GridBagConstraints.BOTH;
        gbc_encryptButton.insets = new Insets(0, 0, 5, 0);
        gbc_encryptButton.gridx = 0;
        gbc_encryptButton.gridy = 0;
        optionSelector.add(encryptButton, gbc_encryptButton);
        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        decryptButton = new JRadioButton("Decrypt");
        GridBagConstraints gbc_decryptButton = new GridBagConstraints();
        gbc_decryptButton.fill = GridBagConstraints.BOTH;
        gbc_decryptButton.gridx = 0;
        gbc_decryptButton.gridy = 1;
        optionSelector.add(decryptButton, gbc_decryptButton);
        group.add(decryptButton);

    }

    private void createActionPanel() {
        BorderLayout bl_panelAction = new BorderLayout();
        JPanel panelAction = new JPanel(bl_panelAction);
        mainFrame.getContentPane().add(
            panelAction,
            BorderLayout.SOUTH
        );
        backButton = new JButton("Back");
        nextButton = new JButton("Continue");
        actionLabel = new JLabel("");
        actionLabel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panelAction.add(backButton, BorderLayout.WEST);
        panelAction.add(actionLabel, BorderLayout.CENTER);
        panelAction.add(nextButton, BorderLayout.EAST);
        nextButton.addActionListener(e -> next());
        backButton.addActionListener(e-> back());
    }

    private boolean next() {
    	switch (phase) {
	        case "alghoritmSelector":
	            if (aesButton.isSelected()) {
	            	removeMainPanel();
	                createAesOptionsSelector();
	                algorithm="aes256";
	                phase="optionSelector";
	                actionLabel.setText("");
	                return true;
	            } else if (rsaButton.isSelected()) {
	            	removeMainPanel();
	                createRsaOptionsSelector();
	                algorithm="rsa";
	                phase="optionSelector";
	                actionLabel.setText("");
	                return true;
	            } else {
	                actionLabel.setText("No algorithm selected");
	                break;
	            }
	        case "optionSelector":
	            if (algorithm.equals("aes256") && !saltText.getText().equals("") && !secretKeyText.getText().equals("")) {
	            	AES256.setSalt(saltText.getText());
	            	AES256.setSecretKey(secretKeyText.getText());
	            	removeMainPanel();
	            	createActionSelector();
	                phase="actionSelector";
	                actionLabel.setText("");
	                return true;
	            } else if (algorithm.equals("rsa") && !publicKeyText.getText().equals("") && !privateKeyText.getText().equals("")) {
	            	RSA.setPublicKey(publicKeyText.getText());
	            	RSA.setPrivateKey(privateKeyText.getText());
	            	removeMainPanel();
	            	createActionSelector();
	                phase="actionSelector";
	                actionLabel.setText("");
	                return true;
	            } else {
	                actionLabel.setText("Fill all options");
	                break;
	            }
	        case "actionSelector":
	            if (encryptButton.isSelected()) {
	            	removeMainPanel();
	            	createTextSelector();
	            	action="encrypt";
	                phase="textSelector";
	                actionLabel.setText("");
	                return true;
	            } else if (decryptButton.isSelected()) {
	            	removeMainPanel();
	            	createTextSelector();
	            	action="decrypt";
	                phase="textSelector";
	                actionLabel.setText("");
	                return true;
	            } else {
	                actionLabel.setText("No action selected");
	                break;
	            }
	        case "textSelector":
	            if (!editorPane.getText().equals("")) {
	                text=editorPane.getText();
	            	removeMainPanel();
	            	createShowResult();
	                phase="showResult";
	            	actionLabel.setText("");
		            nextButton.setText("Restart");
	                return true;
	            } else {
	                actionLabel.setText("No text");
	                break;
	            }
	        case "showResult":
            	removeMainPanel();
            	createAlgorithmSelector();
                phase="alghoritmSelector";
            	actionLabel.setText("");
	            nextButton.setText("Continue");
	        	break;
    	 }
    	 return false;
    }
    private boolean back() {
      	 switch (phase) {
   	        case "alghoritmSelector":
   	          break;
   	        case "optionSelector":
   	       		removeMainPanel();
   	        	createAlgorithmSelector();
   	            phase="alghoritmSelector";
   	            actionLabel.setText("");
   	        	break;
   	        case "actionSelector":
   	       		removeMainPanel();
   	       		if(algorithm.equals("aes256")) {
   	       			createAesOptionsSelector();
   	       		}else if(algorithm.equals("rsa")) {
   	       			createRsaOptionsSelector();
   	       		}
   	       		actionLabel.setText("");
   	       		phase="optionSelector";
   	        	break;
   	        case "textSelector":
	       		removeMainPanel();
	        	createActionSelector();
	            phase="actionSelector";
	            actionLabel.setText("");
	        	break;
	   	     case "showResult":
	         	removeMainPanel();
	         	createTextSelector();
	            phase="textSelector";
	         	actionLabel.setText("");
	            nextButton.setText("Continue");
	        	break;
      	 }
      	 return false;
      }

    private String execute() {
    	switch (algorithm) {
	        case "aes256":
	        	if(action.equals("encrypt")) {
	        		return AES256.encrypt(text);
	        	}else if(action.equals("decrypt")) {
	        		return AES256.decrypt(text);
	        	}
	          break;
	        case "rsa":
	        	if(action.equals("encrypt")) {
	        		return RSA.encrypt(text);
	        	}else if(action.equals("decrypt")) {
	        		return RSA.decrypt(text);
	        	}
	        	break;
	    }
    	return "";
    }
    
    private void removeMainPanel(){
    	Container contentPane = mainFrame.getContentPane();
    	BorderLayout layout = (BorderLayout) contentPane.getLayout();
    	Component currentPanel = layout.getLayoutComponent(BorderLayout.CENTER);
    	if (currentPanel != null) {
    	    contentPane.remove(currentPanel);
    	}
    	contentPane.revalidate();
        contentPane.repaint();
    }
    
    private void createAesOptionsSelector() {
    	createTitle("Set Options (Phase 2/5)");
        JPanel aes256OptionsSelector = new JPanel();

        aes256OptionsSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
            aes256OptionsSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_aes256OptionsSelector = new GridBagLayout();
        gbl_aes256OptionsSelector.columnWidths = new int[]{columnWidth/3, columnWidth/3, columnWidth/3};
        gbl_aes256OptionsSelector.rowHeights = new int[]{columnHeight, columnHeight, 0};
        gbl_aes256OptionsSelector.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_aes256OptionsSelector.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        aes256OptionsSelector.setLayout(gbl_aes256OptionsSelector);
        
        JLabel saltLabel = new JLabel("Salt:");
        GridBagConstraints gbc_saltLabel = new GridBagConstraints();
        gbc_saltLabel.fill = GridBagConstraints.BOTH;
        gbc_saltLabel.insets = new Insets(0, 0, 5, 5);
        gbc_saltLabel.gridx = 0;
        gbc_saltLabel.gridy = 0;
        aes256OptionsSelector.add(saltLabel, gbc_saltLabel);
        
        saltText = new JTextField();
        GridBagConstraints gbc_saltText = new GridBagConstraints();
        gbc_saltText.fill = GridBagConstraints.BOTH;
        gbc_saltText.insets = new Insets(0, 0, 5, 5);
        gbc_saltText.gridx = 1;
        gbc_saltText.gridy = 0;
        aes256OptionsSelector.add(saltText, gbc_saltText);
        saltText.setText(AES256.getSalt());
        
        JButton saltPicker = new JButton("Pick");
        GridBagConstraints gbc_saltPicker = new GridBagConstraints();
        gbc_saltPicker.fill = GridBagConstraints.BOTH;
        gbc_saltPicker.insets = new Insets(0, 0, 5, 0);
        gbc_saltPicker.gridx = 2;
        gbc_saltPicker.gridy = 0;
        aes256OptionsSelector.add(saltPicker, gbc_saltPicker);

        saltPicker.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();

        	int result = fileChooser.showOpenDialog(mainFrame);

        	if (result == JFileChooser.APPROVE_OPTION) {
        		File file = fileChooser.getSelectedFile();

        		try {
        			String content = Files.readString(file.toPath());
        			saltText.setText(content);
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
        	}
        });

        JLabel secretKeyLabel = new JLabel("Secret Key:");
        GridBagConstraints gbc_secretKeyLabel = new GridBagConstraints();
        gbc_secretKeyLabel.fill = GridBagConstraints.BOTH;
        gbc_secretKeyLabel.insets = new Insets(0, 0, 0, 5);
        gbc_secretKeyLabel.gridx = 0;
        gbc_secretKeyLabel.gridy = 1;
        aes256OptionsSelector.add(secretKeyLabel, gbc_secretKeyLabel);
        
        secretKeyText = new JTextField();
        GridBagConstraints gbc_secretKeyText = new GridBagConstraints();
        gbc_secretKeyText.fill = GridBagConstraints.BOTH;
        gbc_secretKeyText.insets = new Insets(0, 0, 0, 5);
        gbc_secretKeyText.gridx = 1;
        gbc_secretKeyText.gridy = 1;
        aes256OptionsSelector.add(secretKeyText, gbc_secretKeyText);
        secretKeyText.setText(AES256.getSecretKey());
        JButton secretKeyPicker = new JButton("Pick");
        GridBagConstraints gbc_secretKeyPicker = new GridBagConstraints();
        gbc_secretKeyPicker.fill = GridBagConstraints.BOTH;
        gbc_secretKeyPicker.gridx = 2;
        gbc_secretKeyPicker.gridy = 1;
        aes256OptionsSelector.add(secretKeyPicker, gbc_secretKeyPicker);
        
        secretKeyPicker.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();
        	int result = fileChooser.showOpenDialog(mainFrame);
        
            if (result == JFileChooser.APPROVE_OPTION) {
            	File file = fileChooser.getSelectedFile();
                try {
                	String content = Files.readString(file.toPath());
                	secretKeyText.setText(content);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }	
            }
        });
    }
    
    private void createRsaOptionsSelector() {
    	createTitle("Set Options (Phase 2/5)");
        JPanel rsaOptionsSelector = new JPanel();

        rsaOptionsSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
        	rsaOptionsSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_rsaOptionsSelector = new GridBagLayout();
        gbl_rsaOptionsSelector.columnWidths = new int[]{columnWidth/3, columnWidth/3, columnWidth/3};
        gbl_rsaOptionsSelector.rowHeights = new int[]{columnHeight, columnHeight, columnHeight, 0};
        gbl_rsaOptionsSelector.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_rsaOptionsSelector.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        rsaOptionsSelector.setLayout(gbl_rsaOptionsSelector);
                
        JLabel publicKeyLabel = new JLabel("PublicKey:");
        GridBagConstraints gbc_publicKeyLabel = new GridBagConstraints();
        gbc_publicKeyLabel.fill = GridBagConstraints.BOTH;
        gbc_publicKeyLabel.insets = new Insets(0, 0, 5, 5);
        gbc_publicKeyLabel.gridx = 0;
        gbc_publicKeyLabel.gridy = 0;
        rsaOptionsSelector.add(publicKeyLabel, gbc_publicKeyLabel);

        publicKeyText = new JTextField();
        GridBagConstraints gbc_publicKeyText = new GridBagConstraints();
        gbc_publicKeyText.fill = GridBagConstraints.BOTH;
        gbc_publicKeyText.insets = new Insets(0, 0, 5, 5);
        gbc_publicKeyText.gridx = 1;
        gbc_publicKeyText.gridy = 0;
        rsaOptionsSelector.add(publicKeyText, gbc_publicKeyText);
        publicKeyText.setText(RSA.getPublicKey());

        JButton publicKeyPicker = new JButton("Pick");
        GridBagConstraints gbc_publicKeyPicker = new GridBagConstraints();
        gbc_publicKeyPicker.fill = GridBagConstraints.BOTH;
        gbc_publicKeyPicker.insets = new Insets(0, 0, 5, 0);
        gbc_publicKeyPicker.gridx = 2;
        gbc_publicKeyPicker.gridy = 0;
        rsaOptionsSelector.add(publicKeyPicker, gbc_publicKeyPicker);

        publicKeyPicker.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();

        	int result = fileChooser.showOpenDialog(mainFrame);

        	if (result == JFileChooser.APPROVE_OPTION) {
        		File file = fileChooser.getSelectedFile();

        		try {
        			String content = Files.readString(file.toPath());
        			publicKeyText.setText(content);
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
        	}
        });
        
        JLabel privateKeyLabel = new JLabel("Private Key:");
        GridBagConstraints gbc_privateKeyLabel = new GridBagConstraints();
        gbc_privateKeyLabel.fill = GridBagConstraints.BOTH;
        gbc_privateKeyLabel.insets = new Insets(0, 0, 5, 5);
        gbc_privateKeyLabel.gridx = 0;
        gbc_privateKeyLabel.gridy = 1;
        rsaOptionsSelector.add(privateKeyLabel, gbc_privateKeyLabel);

        privateKeyText = new JTextField();
        GridBagConstraints gbc_privateKeyText = new GridBagConstraints();
        gbc_privateKeyText.fill = GridBagConstraints.BOTH;
        gbc_privateKeyText.insets = new Insets(0, 0, 5, 5);
        gbc_privateKeyText.gridx = 1;
        gbc_privateKeyText.gridy = 1;
        rsaOptionsSelector.add(privateKeyText, gbc_privateKeyText);
        privateKeyText.setText(RSA.getPrivateKey());
        JButton privateKeyPicker = new JButton("Pick");
        GridBagConstraints gbc_privateKeyPicker = new GridBagConstraints();
        gbc_privateKeyPicker.fill = GridBagConstraints.BOTH;
        gbc_privateKeyPicker.insets = new Insets(0, 0, 5, 0);
        gbc_privateKeyPicker.gridx = 2;
        gbc_privateKeyPicker.gridy = 1;
        rsaOptionsSelector.add(privateKeyPicker, gbc_privateKeyPicker);
        
        privateKeyPicker.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();
        	int result = fileChooser.showOpenDialog(mainFrame);
        
            if (result == JFileChooser.APPROVE_OPTION) {
            	File file = fileChooser.getSelectedFile();
                try {
                	String content = Files.readString(file.toPath());
                	privateKeyText.setText(content);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }	
            }
        });
        
        Button buttonGenerateKeys = new Button("Generate Random Keys");
        buttonGenerateKeys.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                actionLabel.setText(RSA.generateKeys());
                publicKeyText.setText(RSA.getPublicKey());
                privateKeyText.setText(RSA.getPrivateKey());
        	}
        });
        GridBagConstraints gbc_buttonGenerateKeys = new GridBagConstraints();
        gbc_buttonGenerateKeys.fill = GridBagConstraints.BOTH;
        gbc_buttonGenerateKeys.insets = new Insets(0, 0, 0, 5);
        gbc_buttonGenerateKeys.gridx = 1;
        gbc_buttonGenerateKeys.gridy = 2;
        rsaOptionsSelector.add(buttonGenerateKeys, gbc_buttonGenerateKeys);
    }
    
    private void createTextSelector() {
    	createTitle("Set Text (Phase 4/5)");
        JPanel textSelector = new JPanel();

        textSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
        		textSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_textptionsSelector = new GridBagLayout();
        gbl_textptionsSelector.columnWidths = new int[]{columnWidth/3, columnWidth/3, columnWidth/3};
        gbl_textptionsSelector.rowHeights = new int[]{columnHeight, columnHeight, 0, 0};
        gbl_textptionsSelector.columnWeights = new double[]{0, 1.0, 0.0};
        gbl_textptionsSelector.rowWeights = new double[]{0, 1.0, 0.0, Double.MIN_VALUE};
        textSelector.setLayout(gbl_textptionsSelector);
        
        editorPane = new JEditorPane();
        GridBagConstraints gbc_editorPane = new GridBagConstraints();
        gbc_editorPane.gridheight = 2;
        gbc_editorPane.gridwidth = 3;
        gbc_editorPane.insets = new Insets(0, 0, 5, 0);
        gbc_editorPane.fill = GridBagConstraints.BOTH;
        gbc_editorPane.gridx = 0;
        gbc_editorPane.gridy = 0;
        textSelector.add(editorPane, gbc_editorPane);
        
        JButton textPicker = new JButton("Read File");
        GridBagConstraints gbc_textPicker = new GridBagConstraints();
        gbc_textPicker.fill = GridBagConstraints.BOTH;
        gbc_textPicker.gridx = 2;
        gbc_textPicker.gridy = 2;
        textSelector.add(textPicker, gbc_textPicker);

        textPicker.addActionListener(e -> {
        	JFileChooser fileChooser = new JFileChooser();

        	int result = fileChooser.showOpenDialog(mainFrame);

        	if (result == JFileChooser.APPROVE_OPTION) {
        		File file = fileChooser.getSelectedFile();

        		try {
        			String content = Files.readString(file.toPath());
        			editorPane.setText(content);
        		} catch (IOException ex) {
        			ex.printStackTrace();
        		}
        	}
        });  
    }
    private void createShowResult() {
    	createTitle("Show Result (Phase 5/5)");
        JPanel textSelector = new JPanel();

        textSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
        		textSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_textptionsSelector = new GridBagLayout();
        gbl_textptionsSelector.columnWidths = new int[]{columnWidth/3, columnWidth/3, columnWidth/3};
        gbl_textptionsSelector.rowHeights = new int[]{columnHeight, columnHeight, 0, 0};
        gbl_textptionsSelector.columnWeights = new double[]{0.0, 1.0, 0.0};
        gbl_textptionsSelector.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        textSelector.setLayout(gbl_textptionsSelector);
        
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        GridBagConstraints gbc_textPane = new GridBagConstraints();
        gbc_textPane.gridheight = 2;
        gbc_textPane.insets = new Insets(0, 0, 5, 0);
        gbc_textPane.gridwidth = 3;
        gbc_textPane.fill = GridBagConstraints.BOTH;
        gbc_textPane.gridx = 0;
        gbc_textPane.gridy = 0;
        textSelector.add(textPane, gbc_textPane);
        String result=execute();
        textPane.setText(result);
        
        JButton saveButton = new JButton("Save on File");
        GridBagConstraints gbc_saveButton = new GridBagConstraints();
        gbc_saveButton.gridx = 2;
        gbc_saveButton.gridy = 2;
        textSelector.add(saveButton, gbc_saveButton);
        saveButton.addActionListener(e -> {
        	saveFile(result);
        });
    }
    private void saveFile(String textToSave) {
    	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save file");

        int option = fileChooser.showSaveDialog(mainFrame);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            if (!file.getName().toLowerCase().endsWith(".txt")) {
                file = new File(file.getAbsolutePath() + ".txt");
            }
            
            try {
                Files.writeString(
                    file.toPath(),
                    textToSave
                );

                actionLabel.setText("File saved");

            } catch (IOException ex) {
                actionLabel.setText("Error saving file");
                ex.printStackTrace();
            }
        }
    }
}