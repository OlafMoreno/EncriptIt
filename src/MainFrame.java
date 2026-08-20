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

public class MainFrame {

    private JFrame mainFrame;

    private JRadioButton aesButton;
    private JRadioButton rsaButton;
    
    private String phase = "AlghoritmSelector"; 

    private JLabel actionLabel;


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
        createTitle();
        createAlgorithmSelector();
        createActionPanel();
    }

    private void createFrame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Encript It");
        mainFrame.setBounds(100, 100, 400, 450);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBorder(
            new EmptyBorder(15, 15, 15, 15)
        );
        mainFrame.setContentPane(mainPanel);
    }

    private void createTitle() {
        JLabel title = new JLabel("Title");
        mainFrame.getContentPane().add(title, BorderLayout.NORTH);
    }

    private void createAlgorithmSelector() {
        JPanel algorithmSelector = new JPanel();
        algorithmSelector.setBorder(
                new EmptyBorder(20, 20, 20, 20)
            );
        mainFrame.getContentPane().add(
            algorithmSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_algorithmSelector = new GridBagLayout();
        gbl_algorithmSelector.columnWidths = new int[]{314, 0};
        gbl_algorithmSelector.rowHeights = new int[]{101, 101, 101, 0};
        gbl_algorithmSelector.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gbl_algorithmSelector.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        algorithmSelector.setLayout(gbl_algorithmSelector);
        JLabel label = new JLabel("Select algorithm:");
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.fill = GridBagConstraints.BOTH;
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        algorithmSelector.add(label, gbc_label);
        aesButton = new JRadioButton("AES256");
        GridBagConstraints gbc_aesButton = new GridBagConstraints();
        gbc_aesButton.fill = GridBagConstraints.BOTH;
        gbc_aesButton.insets = new Insets(0, 0, 5, 0);
        gbc_aesButton.gridx = 0;
        gbc_aesButton.gridy = 1;
        algorithmSelector.add(aesButton, gbc_aesButton);
        ButtonGroup group = new ButtonGroup();
        group.add(aesButton);
        rsaButton = new JRadioButton("RSA");
        GridBagConstraints gbc_rsaButton = new GridBagConstraints();
        gbc_rsaButton.fill = GridBagConstraints.BOTH;
        gbc_rsaButton.gridx = 0;
        gbc_rsaButton.gridy = 2;
        algorithmSelector.add(rsaButton, gbc_rsaButton);
        group.add(rsaButton);

    }

    private void createActionPanel() {
        BorderLayout bl_panelAction = new BorderLayout();
        JPanel panelAction = new JPanel(bl_panelAction);
        mainFrame.getContentPane().add(
            panelAction,
            BorderLayout.SOUTH
        );
        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Continue");
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
	        case "AlghoritmSelector":
	            if (aesButton.isSelected()) {
	            	removeMainPanel();
	                createAesOptionsSelector();
	                phase="AesOptionsSelector";
	                return true;
	            } else if (rsaButton.isSelected()) {
	            	removeMainPanel();
	                createRsaOptionsSelector();
	                phase="RsaOptionsSelector";
	                return true;
	            } else {
	                actionLabel.setText("No algorithm selected");
	                break;
	            }
    	 }
    	 return false;
    }
    private boolean back() {
      	 switch (phase) {
   	        case "AlghoritmSelector":
   	          break;
   	        case "AesOptionsSelector":
   	       		removeMainPanel();
   	        	createAlgorithmSelector();
   	            phase="AlghoritmSelector";
   	        	break;
   	        case "RsaOptionsSelector":
   	       		removeMainPanel();
   	        	createAlgorithmSelector();
   	            phase="AlghoritmSelector";
   	        	break;
      	 }
      	 return false;
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
        JPanel aes256OptionsSelector = new JPanel();

        aes256OptionsSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
            aes256OptionsSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_aes256OptionsSelector = new GridBagLayout();
        gbl_aes256OptionsSelector.columnWidths = new int[]{104, 104, 104, 0};
        gbl_aes256OptionsSelector.rowHeights = new int[]{147, 147, 0};
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
        
        JTextField saltText = new JTextField();
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
        
        JTextField secretKeyText = new JTextField();
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
        JPanel rsaOptionsSelector = new JPanel();

        rsaOptionsSelector.setBorder(
            new EmptyBorder(20, 20, 20, 20)
        );

        mainFrame.getContentPane().add(
        	rsaOptionsSelector,
            BorderLayout.CENTER
        );
        GridBagLayout gbl_rsaOptionsSelector = new GridBagLayout();
        gbl_rsaOptionsSelector.columnWidths = new int[]{129, 129, 129, 0};
        gbl_rsaOptionsSelector.rowHeights = new int[]{94, 94, 94, 0};
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

        JTextField publicKeyText = new JTextField();
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

        JTextField privateKeyText = new JTextField();
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
}