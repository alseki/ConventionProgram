package View.Account;

import Presenter.AttendeeController.AttendeeController;
import Presenter.Central.SubMenu;
import Presenter.EmployeeController.EmployeeController;
import Presenter.OrganizerController.OrganizerController;
import Presenter.PersonController.PersonController;
import Presenter.SpeakerController.SpeakerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Programmers: Cara McNeil,
// Description: Prints the Main Menu options
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

public class Account implements ActionListener {
    PersonController controller;
    AccountViewFactory accountViewFactory = new AccountViewFactory();
    String[] menuOptions;
    JFrame frame;
    JPanel contentPane;
    JComboBox<String> dropDownMenu;
    JButton logoutButton, submitButton;
    String menuSelection;

    public Account(PersonController controller) {
        this.controller = controller;
        this.menuOptions = controller.getMenuOptions();

        setup();
    }

    public void setup() {
        frame = new JFrame(controller.getMenuTitle()); // Create and set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame

        // menu changes colour depending on the type of user!
        if(this.controller instanceof AttendeeController) {
            contentPane.setBackground(new Color(255, 255, 200)); // Sets AttMenu BG colour to yellow
        } else if(this.controller instanceof OrganizerController){
            contentPane.setBackground(new Color(200, 240, 255)); // Sets OrgMenu BG colour to blue
        } else if(this.controller instanceof SpeakerController) {
            contentPane.setBackground(new Color(255, 200, 200)); // Sets SpeMenu BG colour to pink
        } else if(this.controller instanceof EmployeeController) {
            contentPane.setBackground(new Color(200, 255, 200)); // Sets EmpMenu BG colour to green
        }

        contentPane.setLayout(new FlowLayout());

        logoutButton = new JButton("logout"); // Generates logout button
        logoutButton.setLocation(0, 0);
        logoutButton.setActionCommand("logout");
        logoutButton.setToolTipText("logout of your account without saving");
        contentPane.add(logoutButton);

        submitButton = new JButton("submit"); // Generates submit button
        submitButton.setLocation(0, 0);
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);
        submitButton.setToolTipText("click this button to navigate to the chosen menu");
        contentPane.add(submitButton);

        dropDownMenu = new JComboBox<>(menuOptions);// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
        dropDownMenu.setToolTipText("select a menu to navigate to");
        contentPane.add(dropDownMenu);
    }

    public void runFrom(ActionListener listener) {
        logoutButton.addActionListener(listener);

        // Make frame
        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    public PersonController returnController() {
        frame.setVisible(false);
        return controller;
    }

    
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("submit")) {
            menuSelection = (String)dropDownMenu.getSelectedItem();
            SubMenu subAccountController = controller.createController(menuSelection);
            accountViewFactory.construct(subAccountController);
        }
    }

}
