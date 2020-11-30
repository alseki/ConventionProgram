package View;



import Presenter.Central.ConventionPlanningSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIView implements ActionListener {
    JFrame frame;
    JTextField input;
    JPanel contentPane;
    JLabel introMessage, accountChoiceMessage, loginMessage, savedMessage;
    JButton startButton, continueButton, submitAccountChoiceButton;
    String answer;
    int accountChoice;
    ConventionPlanningSystem cps = new ConventionPlanningSystem();
    // JComboBox<String> dropDownMenu;

    public void run () {
        frame = new JFrame();// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        startButton = new JButton("start");
        startButton.setLocation(0, 0);
        startButton.setActionCommand("start");
        startButton.addActionListener(this);
        contentPane.add(startButton);

        savedMessage = new JLabel("Your changes have been saved.");
        contentPane.add(savedMessage);
        savedMessage.setVisible(false);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    private void intro() {
        frame.setTitle(cps.getIntroTitle());
        introMessage = new JLabel(cps.getIntroMessage());
        contentPane.add(introMessage);

        continueButton = new JButton("continue");
        continueButton.setLocation(0, 0);
        continueButton.setActionCommand("continue");
        continueButton.addActionListener(this);
        contentPane.add(continueButton);
    }

    private void accountChoice() {
        frame.setTitle(cps.getChooseAccountTitle());
        accountChoiceMessage = new JLabel(cps.getChooseAccountMessage());
        contentPane.add(accountChoiceMessage);

        input = new JTextField();
        input.setPreferredSize(new Dimension(400, 24));
        contentPane.add(input, BorderLayout.NORTH);

        submitAccountChoiceButton = new JButton("submit account choice");
        submitAccountChoiceButton.setLocation(0, 0);
        submitAccountChoiceButton.setActionCommand("submit account choice");
        submitAccountChoiceButton.addActionListener(this);
        contentPane.add(submitAccountChoiceButton);
    }

    private void login() {
        // TODO LoginView instantiation?
    }

    private void account() {
        // TODO switch case for type of AccountView
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if (eventName.equals("start")) {
            startButton.setVisible(false);
            intro();
        }

        if (eventName.equals("continue")) {
            continueButton.setVisible(false);
            introMessage.setVisible(false);
            accountChoice();
        }

        if (eventName.equals("submit account choice")) {
            accountChoice = Integer.parseInt(input.getText());
            submitAccountChoiceButton.setVisible(false);
            accountChoiceMessage.setVisible(false);
            input.setText("");
            if (accountChoice == 0) {
                cps.save();
                input.setVisible(false);

                intro();
            }
            else {
                login();
            }
        }

    }
}
