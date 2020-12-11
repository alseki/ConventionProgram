package View.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttMessageMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import View.AccountHelpers.ListDisplayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class AttMessageView extends MessageView {
    AttMessageController controller;
    AttMessageMenu presenter;
    AttMessageMenu announcementPresenter;
    JButton createChatButton, createGroupChatButton;
    JLabel dialogPrompt, dialogPrompt2;
    JTextField inputField2;

    /**
     * The view for attendee users to see their message options.
     * @param controller AttMessageController for handling user input
     */
    public AttMessageView(SubMenu controller) {
        super(controller);
        this.controller = (AttMessageController) controller;
        this.presenter = (AttMessageMenu) controller.getPresenter();
        this.announcementPresenter = ((AttMessageController) controller).getAnChatPresenter();

        contentPane.setBackground(new Color(255, 240, 150));// Sets background colour

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);

        dialogPrompt2 = new JLabel("");
        initializeObject(dialogPrompt2);

        createChatButton = newButton("Create chat");
        createGroupChatButton = newButton("Create group chat");
        createChatButton.setToolTipText("create chat between you and entered user(s)");
    }

    private void showViewAnnouncementChannels() {
        try {
            msgList = new ListDisplayView(announcementPresenter.getChatListTitle(),
                    announcementPresenter.getChats(announcementPresenter.getChatList()));
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.printException(e));
        }
    }

    private void showOpenAnChat() {
        showOpenChat();
        dialogPrompt.setText(announcementPresenter.printChatIdPrompt());
        chooseChat.setText("choose announcement chat");
        chooseChat.setActionCommand("choose announcement chat");
    }

    private void showAnnouncementChat() {
        String chatID = inputField.getText();

        try {
            msgList = new ListDisplayView(announcementPresenter.getChatTitle(chatID),
                    announcementPresenter.getChat(chatID));
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.printException(e));
        }
    }

    private void showCreateChat() {
        dialogPrompt.setText(presenter.printContactUsernamePrompt());
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        createChatButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showCreateGroupChat() {
        dialogPrompt.setText(presenter.printContactUsernamesPrompt());
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        dialogPrompt2.setText("Print the name of the group chat");
        dialogPrompt2.setVisible(true);

        new JTextField(100);
        inputField2.setVisible(true);

        createGroupChatButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void createChat() {
        String participantID = inputField.getText();

        JOptionPane.showConfirmDialog(null, controller.createChat(participantID),
                "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        //try catch block here

        /*
        try {
            if(controller.createChat(participantID).equals(presenter.printChatExists())) {

            }
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printChatNotCreated(e));
            //presenter.printChatNotCreated(new InvalidChoiceException("user"));
        } catch (InvalidFormatException f) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(f));
            //presenter.printChatNotCreated(new
            //        InvalidFormatException("recipients", "You cannot create a chat with yourself!"));
        }

         */


    }

    private void createGroupChat(){ //TODO: -- DONE: convert participantIDs into an array of Strings
        String commaSeparated = inputField.getText();
        ArrayList<String> participantIDs = new ArrayList<>(Arrays.asList(commaSeparated.split(",")));
        String groupName = inputField2.getText();

        /*
        JOptionPane.showConfirmDialog(null, controller.createGroupChat(participantIDs, groupName),
                "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

         */
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(menuOp[5])) {
            hideMainDropDownMenu();
            showViewAnnouncementChannels();
        }

        if(eventName.equals(menuOp[6])) {
            hideMainDropDownMenu();
            showOpenAnChat();
        }

        if(eventName.equals(menuOp[7])) {
            hideMainDropDownMenu();
            showCreateChat();
        }

        if(eventName.equals(menuOp[8])) {
            hideMainDropDownMenu();
            showCreateGroupChat();
        }

        if (eventName.equals("choose announcement chat")) {
            showAnnouncementChat();
            showMainDropDownMenu();
        }

        if (eventName.equals(createChatButton.getActionCommand())) {
            createChat();
            showMainDropDownMenu();
        }

        if(eventName.equals(createGroupChatButton.getActionCommand())) {
            createGroupChat();
            showMainDropDownMenu();
        }
    }
}
