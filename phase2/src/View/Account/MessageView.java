package View.Account;

// Programmer: Ran Yi
// Description: All the methods that take user input in the Message Menu
// Date Created: 01/11/2020
// Date Modified: 08/12/2020

import Presenter.Central.SubMenu;
import Presenter.Exceptions.NoDataException;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.MessageMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MessageView extends AccountView {
    MessageController controller;
    MessageMenu presenter;
    JLabel enterUsernameMsg, allReceivedMessage, contactAdded, allSentMessage;
    JButton submitButton, okayButton;
    JTextField inputAddContact;

    public MessageView(SubMenu controller) {
        super();
        this.controller = (MessageController) controller;
        this.presenter = ((MessageController) controller).getPresenter();


        frame.setTitle(this.presenter.getMenuTitle()); // Create and set up the frame
        //JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 200, 0));// Sets background colour
        contentPane.setLayout(new FlowLayout());

        makeMenuButtons(presenter);

        setupInbox();
        setupSentBox();

        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
        showMainMenuButtons();
    }

    /**
     * ???
     */
    private void setupInbox() {

        okayButton = newButton("okay");

        allReceivedMessage = new JLabel("-YOUR INBOX-");
        allReceivedMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        allReceivedMessage.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(allReceivedMessage);
        allReceivedMessage.setVisible(false);
    }

    /**
     * ???
     */
    private void setupSentBox() {

        okayButton = newButton("okay");

        allSentMessage = new JLabel("-YOUR SENTBOX-");
        allSentMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        allSentMessage.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(allSentMessage);
        allSentMessage.setVisible(false);
    }

    /**
     * Displays a list of the user's Inbox
     */
    private void showViewInbox() throws NoDataException {
        okayButton.setVisible(true);
        String[] myInbox = getInbox();

        if (myInbox != null && myInbox.length > 0) {
            StringBuilder messagesReceived = new StringBuilder();
            for (String myMessageReceived : myInbox) {
                messagesReceived.append(myMessageReceived).append(", ");
            }
            allReceivedMessage.setText(messagesReceived.toString());
        } else {
            allReceivedMessage.setText(presenter.printNoMessageReceived());
        }
        allReceivedMessage.setVisible(true);
    }

    /**
     *
     */
    private String[] getInbox() throws NoDataException {
        return controller.inBox();
    }

    /**
     * Displays a list of the user's Inbox
     */
    private void showViewSentBox() throws NoDataException {
        okayButton.setVisible(true);
        String[] mySentBox = getSentBox();

        if (mySentBox != null && mySentBox.length > 0) {
            StringBuilder messagesSent = new StringBuilder();
            for (String myMessageSent : mySentBox) {
                messagesSent.append(myMessageSent).append(", ");
            }
            allSentMessage.setText(messagesSent.toString());
        } else {
            allSentMessage.setText(presenter.printNoMessageReceived());
        }
        allSentMessage.setVisible(true);
    }

    /**
     *
     */
    private String[] getSentBox() throws NoDataException {
        return controller.sentBox();
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        // [0] = View Contacts
        // [1] = Add Contact
        if (eventName.equals(menuButtons.get(0).getActionCommand())) {
            hideMainMenuButtons();
            try {
                showViewInbox();
            } catch (NoDataException e) {
                e.printStackTrace();
            }
        }

        if (eventName.equals(menuButtons.get(1).getActionCommand())) {
            hideMainMenuButtons();
            try {
                showViewSentBox();
            } catch (NoDataException e) {
                e.printStackTrace();
            }
        }

        /*if(eventName.equals("okay")) {
            showMainMenuButtons();
        }

        if(eventName.equals("submit")) {
            addContact();
        }*/

    }



}
