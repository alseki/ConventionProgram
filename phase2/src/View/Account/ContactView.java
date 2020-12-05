package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.ContactMenu;
import Presenter.PersonController.PersonController;

import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContactView extends AccountView {
    ContactController controller;
    ContactMenu presenter;

    Scanner input = new Scanner(System.in);

    JFrame frame;
    JPanel contentPane;
    JButton addContactButton, viewContactListButton;
    JTextField inputAddContact;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
        this.presenter = ((ContactController) controller).getPresenter();
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

    /**
     * Calls the Contact Controller to retrieve the current user's contact list
     * @return a non-empty array of strings that represent the user's contacts
     */
    private String[] getContactList() {
        try {
            return controller.getContactList();
        } catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
        }
        return null;
    }

    /**
     * Calls the Contact Controller to try to add a contact to the user's contact list
     */
    private void addContact() {
        String contactUsername = ""; // FIXME set to user's input
        try {
            controller.addContact(contactUsername);
        } catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
        }
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
