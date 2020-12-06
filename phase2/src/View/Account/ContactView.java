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
    JLabel enterUsernameMsg, allContacts;
    JButton addContactButton, viewContactListButton, submitButton, okayButton;
    JTextField inputAddContact;

    public ContactView(SubMenu controller) {
        super();
        this.controller = (ContactController) controller;
        this.presenter = ((ContactController) controller).getPresenter();


        frame = new JFrame(this.presenter.getMenuTitle()); // Create and set up the frame
        //JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 200, 0));// Sets background colour
        contentPane.setLayout(new FlowLayout());

        viewContactListButton = newButton(this.presenter.getMenuOptions()[1]); // Generates "view list" button
        contentPane.add(viewContactListButton);

        addContactButton = newButton(this.presenter.getMenuOptions()[2]); // Generates "add contact" button
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

    private void setupAddContact() {
        enterUsernameMsg = new JLabel(this.presenter.printAddContactPrompt());
        contentPane.add(enterUsernameMsg);
        enterUsernameMsg.setVisible(false);

        submitButton = newButton("submit");
        contentPane.add(submitButton);
        submitButton.setVisible(false);
    }

    private void setupViewContacts() {
        //enterUsernameMsg = new JLabel(this.presenter.printAddContactPrompt());
        //contentPane.add(enterUsernameMsg);
        //enterUsernameMsg.setVisible(false);
    }


    /**
     * Calls the Contact Controller to retrieve the current user's contact list
     * @return a non-empty array of strings that represent the user's contacts
     */
    private String[] getContactList() {
        enterUsernameMsg.setVisible(false);
        addContactButton.setVisible(false);
        viewContactListButton.setVisible(false);

        okayButton.setVisible(true);

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
        addContactButton.setVisible(false);
        viewContactListButton.setVisible(false);

        enterUsernameMsg.setVisible(true);
        submitButton.setVisible(true);

        String contactUsername = input.nextLine();
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

        if (eventName.equals(this.presenter.getMenuOptions()[1])) {
            String[] myContacts = getContactList();
            String contacts = "";
            for(int i = 0; i < myContacts.length - 1; i++) {
                contacts += myContacts[i] + ", ";
            }

            allContacts = new JLabel(contacts);//Create and add label that is centered and has empty borders
            allContacts.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            allContacts.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
            contentPane.add(allContacts);

        }

        if (eventName.equals(this.presenter.getMenuOptions()[2])) {

            addContact();

        }
    }

    private JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        //newButton.addActionListener(this);
        return newButton;
    }
}
