package View.Account;

// Programmer: Sarah Kronenfeld, Ran Yi
// Description: All the methods that take user input in the Message Menu
// Date Created: 01/11/2020
// Date Modified: 08/12/2020

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.MessageMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class MessageView extends AccountView {
    MessageController controller;
    MessageMenu presenter;
    JLabel dialogPrompt, dialogPrompt2;
    JButton sendMsg, chooseChat;
    ListDisplayView msgList;
    JTextField inputField;
    JTextArea messageField;

    /**
     * The view for users to see their convention message options.
     * @param controller MessageController for handling user input
     */
    public MessageView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (MessageController) controller;
        presenter = (MessageMenu) controller.getPresenter();

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);

        inputField = new JTextField(20);
        initializeObject(inputField);

        dialogPrompt2 = new JLabel("");
        initializeObject(dialogPrompt2);

        messageField = new JTextArea(5, 20);
        messageField.setPreferredSize(new Dimension(20, 20));
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        initializeObject(messageField);

        sendMsg = newButton("Send message");
        sendMsg.setToolTipText("send the entered message to entered user(s)");
        chooseChat = newButton("choose chat");
        chooseChat.setToolTipText("choose a chat to view");
    }

    private void showCheckInbox() {
        try {
            msgList = new ListDisplayView(presenter.getInboxTitle(), presenter.getInBox());
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainDropDownMenu();
        }
    }

    private void showCheckSentBox() {
        try {
            msgList = new ListDisplayView(presenter.getOutboxTitle(), presenter.getOutBox());
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainDropDownMenu();
        }
    }

    private void showViewChats() {
        try {
            msgList = new ChatListDisplayView(presenter.getChatListTitle(), presenter.getChats(presenter.getChatList()));
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainDropDownMenu();
        }
    }

    protected void showOpenChat() {
        dialogPrompt.setText(presenter.printChatIdPrompt());
        dialogPrompt.setVisible(true);

        inputField.setVisible(true);

        chooseChat.setText("choose chat");
        chooseChat.setActionCommand("choose chat");
        chooseChat.setVisible(true);
        backButton.setVisible(true);
    }

    private void showViewMsgsInChat() {
        String chatID = inputField.getText(); //FIXME: now we can use Chat.name
        try {
            msgList = new ListDisplayView(presenter.getChatTitle(chatID), presenter.getChat(chatID));
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainDropDownMenu();
        }
    }

    private void showSendMsg() {
        dialogPrompt.setText(presenter.printChatIdMessagePrompt());
        dialogPrompt.setVisible(true);

        inputField.setVisible(true);

        dialogPrompt2.setText(presenter.printContentPrompt());
        dialogPrompt2.setVisible(true);

        messageField.setVisible(true);

        sendMsg.setVisible(true);
        backButton.setVisible(true);
    }

    //FIXME: this method won't work until MessageController's send Message method is fixed
    private void sendMsg() {
        String chatID = inputField.getText();
        String msg = messageField.getText();

        try {
            JOptionPane.showConfirmDialog(null, controller.sendMessageChoice(chatID, msg),
                    "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        } catch (InvalidChoiceException e){
            exceptionDialogBox(presenter.exceptionTitle(), "Wrongg");
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(this.menuOp[0])) {
            hideMainDropDownMenu();
            showCheckInbox();
        }

        if(eventName.equals(this.menuOp[1])) {
            hideMainDropDownMenu();
            showCheckSentBox();
        }

        if(eventName.equals(this.menuOp[2])) {
            hideMainDropDownMenu();
            showViewChats();
        }

        if(eventName.equals(this.menuOp[3])) {
            hideMainDropDownMenu();
            showOpenChat();
        }

        if(eventName.equals(this.menuOp[4])) {
            hideMainDropDownMenu();
            showSendMsg();
        }

        if (eventName.equals(chooseChat.getActionCommand())) {
            showViewMsgsInChat();
            showMainDropDownMenu();
        }

        if (eventName.equals(sendMsg.getActionCommand())) {
            sendMsg();
            showMainDropDownMenu();
        }

    }



}
