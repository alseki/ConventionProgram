package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.PersonController;

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
    JButton logoutButton;
    String menuSelection;

    public Account(PersonController controller) {
        this.controller = controller;
        this.menuOptions = controller.getMenuOptions();

        setup();
    }

    public void setup() {
        frame = new JFrame(controller.getMenuTitle()); // Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        logoutButton = new JButton("logout"); // Generates logout button
        logoutButton.setLocation(0, 0);
        logoutButton.setActionCommand("logout");
        contentPane.add(logoutButton);

        dropDownMenu = new JComboBox<String>(menuOptions);// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
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
        String eventName = event.getActionCommand();

        JComboBox<String> comboBox = (JComboBox<String>)event.getSource();
        menuSelection = (String)comboBox.getSelectedItem();

        SubMenu subAccountController = controller.createController(menuSelection);
        accountViewFactory.construct(subAccountController);

    }

}
