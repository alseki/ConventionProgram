package View.Central;

import Presenter.PersonController.ContactController;
import Presenter.PersonController.PersonController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Programmers: Cara McNeil,
// Description: Prints the Main Menu options
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

public abstract class PersonAccount implements ActionListener {
    PersonController controller;
    String[] arrMenuOptions;
    JFrame frame;
    JPanel contentPane;
    ArrayList<String> menuOptions = new ArrayList<>();
    JComboBox<String> dropDownMenu;
    //JLabel ;
    JButton logoutButton;
    String menuSelection;

    public PersonAccount(PersonController controller) {
        this.controller = controller;
        menuOptions.add("Contact Menu");
        menuOptions.add("Message Menu");

        frame = new JFrame(); // Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        logoutButton = new JButton("logout"); // Generates logout button
        logoutButton.setLocation(0, 0);
        logoutButton.setActionCommand("logout");
        logoutButton.addActionListener(this);
        contentPane.add(logoutButton);

        dropDownMenu = new JComboBox<String>(arrMenuOptions);// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
        contentPane.add(dropDownMenu);

        // Make frame
        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if (eventName.equals("logout")) {
            // TODO save, return to gui menu
            return; // FIXME
        }

        JComboBox<String> comboBox = (JComboBox<String>)event.getSource();
        menuSelection = (String)comboBox.getSelectedItem();

        if (menuSelection == "Contact Menu") {
            // TODO call ContactMenu(), contactcontroller
            return; // FIXME
        }

        if (menuSelection == "Message Menu") {
            // TODO call MessageMenu(), messagecontroller
            return; // FIXME
        }

    }

}
