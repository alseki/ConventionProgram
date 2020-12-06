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

    //Scanner input = new Scanner(System.in);

    JFrame frame;
    JLabel enterUsernameMsg, allContacts, noContactsMsg, viewC, contactAdded;
    JButton addContactButton, viewContactListButton, submitButton, okayButton;
    JTextField inputAddContact;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
        this.presenter = ((ContactController) controller).getPresenter();


        frame = new JFrame(this.presenter.getMenuTitle()); // Create and set up the frame
        //JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 200, 0));// Sets background colour
        contentPane.setLayout(new FlowLayout());

        viewContactListButton = newButton(this.presenter.getMenuOptions()[0]); // Generates "view list" button
        contentPane.add(viewContactListButton);

        addContactButton = newButton(this.presenter.getMenuOptions()[1]); // Generates "add contact" button
        contentPane.add(addContactButton);

        okayButton = newButton("okay");
        contentPane.add(okayButton);
        okayButton.setVisible(false);

        setupAddContact();
        setupViewContacts();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);

    }

    // ok so lines 33-57 used to be in this run method until i moved it into the constructor
    // and it worked that way lol hmmmm
    /*
    public void run() {

    }
     */

    /**
     *
     */
    private void showContactMain() {
        hideAll();
        viewContactListButton.setVisible(true);
        addContactButton.setVisible(true);
    }

    /**
     * Hides the main screen button options
     */
    private void hideContactMain() {
        addContactButton.setVisible(false);
        viewContactListButton.setVisible(false);
    }

    private void setupAddContact() {
        enterUsernameMsg = new JLabel(this.presenter.printAddContactPrompt());
        contentPane.add(enterUsernameMsg);
        enterUsernameMsg.setVisible(false);

        inputAddContact = new JTextField(20);
        contentPane.add(inputAddContact);
        inputAddContact.setVisible(false);

        submitButton = newButton("submit");
        contentPane.add(submitButton);
        submitButton.setVisible(false);

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

        if (eventName.equals(this.presenter.getMenuOptions()[0])) {
            hideContactMain();
            showViewContacts();
        }

        if (eventName.equals(this.presenter.getMenuOptions()[1])) {
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

    private JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        newButton.addActionListener(this);
        return newButton;
    }
}
