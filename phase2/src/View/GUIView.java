package View;

// Contributors: Cara McNeil, Sarah Kronenfeld
// Last edit: December 2 2020

// Architecture Level - UI



import Presenter.Central.ConventionPlanningSystem;
import Presenter.PersonController.PersonController;
import View.Central.*;
import View.Central.Account.Account;

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


    /**
     * Sets up the page
     */
    public void run () {
        frame = new JFrame();// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        startButton = newButton("start");
        contentPane.add(startButton);

        savedMessage = new JLabel("Your changes have been saved. Exit the program or click 'okay' to go back to " +
                "welcome screen.");
        contentPane.add(savedMessage);
        savedMessage.setVisible(false);

        okayButton = newButton("okay");
        contentPane.add(okayButton);
        okayButton.setVisible(false);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }


    /**
     * Method that runs an intro for a user
     */
    private void intro() {
        frame.setVisible(true);
        frame.setTitle(cps.getIntroTitle());
        introMessage = new JLabel(cps.getIntroMessage());
        contentPane.add(introMessage);

        continueButton = newButton("continue");
        contentPane.add(continueButton);
    }


    /**
     * Method to get a choice of account from the user
     */
    private void accountChoice() {
        frame.setTitle(cps.getChooseAccountTitle());
        accountChoiceMessage = new JLabel(cps.getChooseAccountMessage());
        contentPane.add(accountChoiceMessage);

        input = new JTextField();
        input.setPreferredSize(new Dimension(400, 24));
        contentPane.add(input, BorderLayout.NORTH);

        submitAccountChoiceButton = newButton("submit account choice");
        contentPane.add(submitAccountChoiceButton);
    }

    /**
     * Method to run a login program for the user
     * @param controller The current personcontroller
     * @return that controller, updated
     */
    private PersonController login(PersonController controller) {
        LoginView view = new LoginView(controller.getLogin());
        String id = view.run();
        if (!(id.equals("0"))) {
            controller.logIn(id);
            return controller;
        }
        else {
            return null;
        }
    }


    /**
     * Detects changes to component parts of the GUIView
     * @param event the event that's happened
     */
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
                PersonController controller = cps.getController(accountChoice);
                frame.setVisible(false);
                controller = login(controller);
                if (controller == null) {
                    frame.setVisible(true);
                    accountChoice();
                }
                else {
                    new Account(controller);
                }
            }
        }

        if (eventName.equals("okay")) {
            savedMessage.setVisible(false);
            okayButton.setVisible(false);
            intro();
        }

    }

    private JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        newButton.addActionListener(this);
        return newButton;
    }
}
