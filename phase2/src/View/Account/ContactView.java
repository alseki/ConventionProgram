package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.ContactMenu;
import Presenter.PersonController.PersonController;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContactView extends AccountView {
    ContactController controller;
    ContactMenu presenter;
    JLabel enterUsernameMsg, allContacts, viewC, contactAdded;
    JButton submitButton, okayButton;
    JTextField inputAddContact;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
        this.presenter = ((ContactController) controller).getPresenter();


        frame.setTitle(this.presenter.getMenuTitle()); // Create and set up the frame
        //JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 200, 0));// Sets background colour
        contentPane.setLayout(new FlowLayout());

        makeMenuButtons(presenter);

        setupAddContact();
        setupViewContacts();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
        showContactMain();
    }

    /**
     * Shows the main Contact Menu screen
     */
    private void showContactMain() {
        hideAll();
        for (JButton button: menuButtons) {
            button.setVisible(true);
        }
    }

    /**
     * Hides the main screen button options
     */
    private void hideContactMain() {
        for (JButton button: menuButtons) {
            button.setVisible(false);
        }
    }

    /**
     * Adds addContact components to contentPane
     */
    private void setupAddContact() {
        enterUsernameMsg = new JLabel(this.presenter.printAddContactPrompt());
        contentPane.add(enterUsernameMsg);
        enterUsernameMsg.setVisible(false);

        inputAddContact = new JTextField(20);
        contentPane.add(inputAddContact);
        inputAddContact.setVisible(false);

        submitButton = newButton("submit");

        contactAdded = new JLabel(this.presenter.printContactAdded());
        contentPane.add(contactAdded);
        contactAdded.setVisible(false);
    }

    /**
     * ???
     */
    // FIXME what's the point of viewC?
    private void setupViewContacts() {
        viewC = new JLabel("viewing your contacts...");
        contentPane.add(viewC);
        viewC.setVisible(false);

        okayButton = newButton("okay");

        allContacts = new JLabel();//Create and add label that is centered and has empty borders
        allContacts.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        allContacts.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(allContacts);
        allContacts.setVisible(false);
    }

    /**
     * Displays a list of the user's contacts
     */
    private void showViewContacts() {
        okayButton.setVisible(true);
        viewC.setVisible(true);
        String[] myContacts = getContactList();

        if (myContacts != null && myContacts.length > 0) {
            StringBuilder contacts = new StringBuilder();
            for (String myContact : myContacts) {
                contacts.append(myContact).append(", ");
            }
            allContacts.setText(contacts.toString());
        } else {
            allContacts.setText(presenter.printNoContacts());
        }
        allContacts.setVisible(true);
    }

    /**
     * Calls the Contact Controller to retrieve the current user's contact list
     * @return a non-empty array of strings that represent the user's contacts
     */
    private String[] getContactList() {
        return controller.getContactList();
    }

    /**
     * Shows components that prompt a user to add contact information
     */
    private void showAddContact() {
        enterUsernameMsg.setVisible(true);
        submitButton.setVisible(true);
        inputAddContact.setVisible(true);
    }

    /**
     * Calls the Contact Controller to try to add a contact to the user's contact list
     */
    private void addContact() {
        enterUsernameMsg.setVisible(false);
        submitButton.setVisible(false);
        inputAddContact.setVisible(false);

        String contactUsername = inputAddContact.getText();

        try {
            controller.addContact(contactUsername);
            contactAdded.setVisible(true);
            okayButton.setVisible(true);
        } catch (InvalidChoiceException e) {
            JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), presenter.printException(e),
                    JOptionPane.DEFAULT_OPTION);
            showContactMain();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        // [0] = View Contacts
        // [1] = Add Contact
        if (eventName.equals(menuButtons.get(0).getActionCommand())) {
            hideContactMain();
            showViewContacts();
        }

        if (eventName.equals(menuButtons.get(1).getActionCommand())) {
            hideContactMain();
            showAddContact();
        }

        if(eventName.equals("okay")) {
            showContactMain();
        }

        if(eventName.equals("submit")) {
            addContact();
        }
    }
}
