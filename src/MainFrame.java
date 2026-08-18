import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

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
    }

    private boolean next() {
    	 switch (phase) {
	        case "AlghoritmSelector":
	            if (aesButton.isSelected()) {
	                actionLabel.setText("AES256");
	                return true;
	            } else if (rsaButton.isSelected()) {
	                actionLabel.setText("RSA");
	                return true;
	            } else {
	                actionLabel.setText("No algorithm selected");
	                break;
	            }
    	 }
    	 return false;
    }
    private void removePanel(){
    	Container contentPane = mainFrame.getContentPane();
    	BorderLayout layout = (BorderLayout) contentPane.getLayout();
    	Component currentPanel = layout.getLayoutComponent(BorderLayout.CENTER);
    	if (currentPanel != null) {
    	    contentPane.remove(currentPanel);
    	}
    	contentPane.revalidate();
        contentPane.repaint();
    }
}