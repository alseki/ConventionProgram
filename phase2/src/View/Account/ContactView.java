package View.Account;

import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.PersonController;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContactView extends AccountView {
    ContactController controller;

    Scanner input = new Scanner(System.in);

    JFrame frame;
    JPanel contentPane;
    JButton addContactButton, viewContactListButton;
    JTextField inputAddContact;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
    }

    public void setup() {
        frame = new JFrame("CONTACT MENU"); // Create and set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(0, 255, 0));// Sets background colour to ugly green
        contentPane.setLayout(new FlowLayout());

        addContactButton = new JButton("Add a contact"); // Generates "add contact" button
        addContactButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        addContactButton.setActionCommand("add");
        contentPane.add(addContactButton);

        viewContactListButton = new JButton("View your contact list"); // Generates "view list" button
        viewContactListButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        viewContactListButton.setActionCommand("view");
        contentPane.add(viewContactListButton);

        //frame.setContentPane(contentPane);
        //frame.pack();
        //frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if (eventName.equals("add")) {
            addContactButton.setVisible(false);
            //controller.addContact();
            //addContactButton.setText("test text");
            //addContactButton.setActionCommand("asdffsgdfh");

        } else if (eventName.equals("view")) {
            addContactButton.setVisible(false);
            //viewContactListButton.setText("jgfjhgfd");
            //viewContactListButton.setActionCommand("test");
        }
    }
}
