import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
        algorithmSelector.setLayout(new GridLayout(3, 2, 0, 0));
        mainFrame.getContentPane().add(
            algorithmSelector,
            BorderLayout.CENTER
        );
        JLabel label = new JLabel("Select algorithm:");
        algorithmSelector.add(label);
        ButtonGroup group = new ButtonGroup();
        aesButton = new JRadioButton("AES256");
        rsaButton = new JRadioButton("RSA");
        algorithmSelector.add(aesButton);
        algorithmSelector.add(rsaButton);
        group.add(aesButton);
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

        aes256OptionsSelector.setLayout(new GridLayout(0, 3, 0, 10));

        mainFrame.getContentPane().add(
            aes256OptionsSelector,
            BorderLayout.CENTER
        );

        JLabel saltLabel = new JLabel("Salt:");
        aes256OptionsSelector.add(saltLabel);
        
        JTextField saltText = new JTextField();
        aes256OptionsSelector.add(saltText);
        saltText.setText(AES256.getSalt());

        JButton saltPicker = new JButton("Pick");
        aes256OptionsSelector.add(saltPicker);
        
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
        aes256OptionsSelector.add(secretKeyLabel);
        
        JTextField secretKeyText = new JTextField();
        aes256OptionsSelector.add(secretKeyText);
        secretKeyText.setText(AES256.getSecretKey());
        JButton secretKeyPicker = new JButton("Pick");
        aes256OptionsSelector.add(secretKeyPicker);
        
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

        rsaOptionsSelector.setLayout(new GridLayout(0, 3, 0, 10));

        mainFrame.getContentPane().add(
        	rsaOptionsSelector,
            BorderLayout.CENTER
        );

        JLabel publicKeyLabel = new JLabel("PublicKey:");
        rsaOptionsSelector.add(publicKeyLabel);
        
        JTextField publicKeyText = new JTextField();
        rsaOptionsSelector.add(publicKeyText);
        publicKeyText.setText(RSA.getPublicKey());

        JButton publicKeyPicker = new JButton("Pick");
        rsaOptionsSelector.add(publicKeyPicker);
        
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
        rsaOptionsSelector.add(privateKeyLabel);
        
        JTextField privateKeyText = new JTextField();
        rsaOptionsSelector.add(privateKeyText);
        privateKeyText.setText(RSA.getPrivateKey());
        JButton privateKeyPicker = new JButton("Pick");
        rsaOptionsSelector.add(privateKeyPicker);
        
        Button buttonGenerateKeys = new Button("Generate Random Keys");
        buttonGenerateKeys.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                actionLabel.setText(RSA.generateKeys());
                publicKeyText.setText(RSA.getPublicKey());
                privateKeyText.setText(RSA.getPrivateKey());
        	}
        });
        rsaOptionsSelector.add(buttonGenerateKeys);
        
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
    }
}