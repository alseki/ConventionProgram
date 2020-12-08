package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
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
    JLabel enterUsernameMsg, contactAdded;
    JButton submitButton;
    JTextField inputAddContact;
    ListDisplayView allContacts;

    public ContactView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (ContactController) controller;
        this.presenter = ((ContactController) controller).getPresenter();
        contentPane.setBackground(yellowBG);// Sets background colour

        setupAddContact();
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
     * Displays a list of the user's contacts
     */
    private void showViewContacts() {
        try {
            String[] myContacts = presenter.getContactList();
            allContacts = new ListDisplayView(presenter.getContactListTitle(), myContacts);
        } catch (NoDataException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
            showMainMenuButtons();
        }
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
            showMainMenuButtons();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        String eventName = event.getActionCommand();

        // [0] = View Contacts
        // [1] = Add Contact
        if (eventName.equals(menuButtons.get(0).getActionCommand())) {
            hideMainMenuButtons();
            showViewContacts();
        }

        if (eventName.equals(menuButtons.get(1).getActionCommand())) {
            hideMainMenuButtons();
            showAddContact();
        }

        if(eventName.equals("submit")) {
            addContact();
        }
    }
}
