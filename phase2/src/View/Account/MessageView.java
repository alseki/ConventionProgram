package View.Account;

// Programmer: Ran Yi
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
    JTextField inputField, messageField;
    JButton okayButton, continueButton;
    private String[] menuOp;

    public MessageView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (MessageController) controller;
        presenter = (MessageMenu)controller.getPresenter();

        this.menuOp = this.presenter.getMenuOptions();

        okayButton = newButton("okay");
        initializeObject(okayButton);
        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);
        inputField = new JTextField(100);
        initializeObject(inputField);
        messageField = new JTextField(100);
        initializeObject(messageField);
        continueButton = newButton("continue");
        initializeObject(continueButton);
    }


    @Override
    public void showMainDropDownMenu() {
        super.showMainDropDownMenu();
        continueButton.setVisible(true);
    }

    @Override
    public void hideMainDropDownMenu() {
        super.hideMainDropDownMenu();
        continueButton.setVisible(false);
    }


    private void showCheckInbox() {
        try {
            msgList = new ListDisplayView(presenter.getInboxTitle(), presenter.getInBox());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainMenuButtons();
        }
    }

    private void showCheckSentBox() {
        try {
            msgList = new ListDisplayView(presenter.getOutboxTitle(), presenter.getOutBox());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainMenuButtons();
        }
    }

    private void showViewChats() {
        try {
            msgList = new ListDisplayView(presenter.getChatListTitle(), presenter.getChatList());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainMenuButtons();
        }
    }

    protected void showOpenChat() {
        dialogPrompt = new JLabel(presenter.printChatIdPrompt());
        dialogPrompt.setVisible(true);

        inputField = new JTextField(100);
        inputField.setVisible(true);

        okayButton.setActionCommand("choose chat");
        okayButton.setVisible(true);
    }

    private void showViewMsgsInChat() {
        String chatID = inputField.getText();
        try {
            msgList = new ListDisplayView(presenter.getChatTitle(chatID), presenter.getChat(chatID));
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainMenuButtons();
        }
    }

    private void showSendMsg() {
        //dialogPrompt = new JLabel(presenter.printContentPrompt()); TODO: make this method return string
        dialogPrompt.setVisible(true);

        inputField = new JTextField(100);
        inputField.setVisible(true);
        messageField.setVisible(true);

        okayButton.setActionCommand("send msg");
        okayButton.setVisible(true);
    }

    private void sendMsg() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if(eventName.equals(this.menuOp[1])) {
            hideMainMenuButtons();
            showCheckInbox();
        }

        if(eventName.equals(this.menuOp[2])) {
            hideMainMenuButtons();;
            showCheckSentBox();
        }

        if(eventName.equals(this.menuOp[3])) {
            hideMainMenuButtons();
            showViewChats();
        }

        if(eventName.equals(this.menuOp[4])) {
            hideMainMenuButtons();
            showOpenChat();
        }

        if(eventName.equals(this.menuOp[5])) {
            hideMainMenuButtons();
            showSendMsg();
        }

        if (eventName.equals("choose chat")) {
            showViewMsgsInChat();
            showMainMenuButtons();
        }

        if (eventName.equals("send msg")) {
            sendMsg();
            showMainMenuButtons();
        }

    }



}
