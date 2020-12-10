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

public class MessageView extends AccountView {
    MessageController controller;
    MessageMenu presenter;
    JLabel dialogPrompt;
    ListDisplayView msgList;
    JTextField inputField;
    JTextArea messageField;
    /*JScrollPane scrollPane;*/ // TODO might add this in later

    public MessageView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (MessageController) controller;
        presenter = (MessageMenu)controller.getPresenter();

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);
        inputField = new JTextField(20);
        initializeObject(inputField);

        messageField = new JTextArea(5, 20);
        messageField.setPreferredSize(new Dimension(20, 20));
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        initializeObject(messageField);

        /*scrollPane = new JScrollPane(messageField);
        contentPane.add(messageField, BorderLayout.CENTER);
        scrollPane.setVisible(false);*/
    }


    @Override
    public void showMainDropDownMenu() {
        super.showMainDropDownMenu();
    }

    @Override
    public void hideMainDropDownMenu() {
        super.hideMainDropDownMenu();
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
            msgList = new ListDisplayView(presenter.getChatListTitle(), presenter.getChatList());
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

        okayButton.setText("choose chat");
        okayButton.setActionCommand("choose chat");
        okayButton.setVisible(true);
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
        //dialogPrompt = new JLabel(presenter.printContentPrompt()); TODO: make this method return string
        dialogPrompt.setVisible(true);

        inputField.setVisible(true);
        messageField.setVisible(true);

        okayButton.setActionCommand("send msg");
        okayButton.setText("send msg");
        okayButton.setVisible(true);
    }

    //TODO: send msg method is in MessageController.
    private void sendMsg() {

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

        if (eventName.equals("choose chat")) {
            showViewMsgsInChat();
            showMainDropDownMenu();
        }

        if (eventName.equals("send msg")) {
            sendMsg();
            showMainDropDownMenu();
        }

    }



}
