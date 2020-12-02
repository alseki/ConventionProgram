package View;



import Presenter.AttendeeController.AttEventMenu;
import Presenter.AttendeeController.AttendeeController;
import Presenter.Central.ConventionPlanningSystem;
import Presenter.PersonController.LoginController;
import View.Central.AttendeeAccount;
import View.Central.LoginView;
import View.Central.OrganizerAccount;
import View.Central.SpeakerAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIView implements ActionListener {
    JFrame frame;
    JTextField input;
    JPanel contentPane;
    JLabel introMessage, accountChoiceMessage, loginMessage, savedMessage;
    JButton startButton, continueButton, submitAccountChoiceButton, okayButton;
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

        savedMessage = new JLabel("Your changes have been saved. Exit the program or click 'okay' to go back to " +
                "welcome screen.");
        contentPane.add(savedMessage);
        savedMessage.setVisible(false);

        okayButton = new JButton("okay");
        okayButton.setLocation(0, 0);
        okayButton.setActionCommand("okay");
        okayButton.addActionListener(this);
        contentPane.add(okayButton);
        okayButton.setVisible(false);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    private void intro() {
        frame.setVisible(true);
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
        LoginView view = new LoginView();
        if (!(view.run().equals("0"))) {
            account();
        }
        // TODO else branch that throws an exception
    }

    private void account() {
        switch (accountChoice) {
            case 1:
                new AttendeeAccount(cps.getController(accountChoice)).run();
            case 2:
                new OrganizerAccount(cps.getController(accountChoice)).run();
            case 3:
                new SpeakerAccount(cps.getController(accountChoice)).run();
        }

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
            input.setVisible(false);
            if (accountChoice == 0) {
                cps.save();
                savedMessage.setVisible(true);
                okayButton.setVisible(true);
            }
            else {
                frame.setVisible(false);
                login();
            }
        }

        if (eventName.equals("okay")) {
            savedMessage.setVisible(false);
            okayButton.setVisible(false);
            intro();
        }

    }
}
