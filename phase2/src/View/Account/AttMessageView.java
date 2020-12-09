package View.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttMessageMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;

import javax.swing.*;
import java.awt.event.ActionEvent;


//FIXME selecting "View messages" as an Attendee doesn't even launch the AttMsg menu... (???)

public class AttMessageView extends MessageView {
    AttMessageController controller;
    AttMessageMenu presenter;
    AttMessageMenu announcementPresenter;
    private String[] menuOp;

    public AttMessageView(SubMenu controller) {
        super(controller);
        this.controller = (AttMessageController) controller;
        this.presenter = ((AttMessageController) controller).getPresenter();
        this.announcementPresenter = ((AttMessageController) controller).getAnChatPresenter();

        this.menuOp = this.presenter.getMenuOptions();
        contentPane.setBackground(pinkBG);// Sets background colour
    }

    private void showViewAnnouncementChannels() {
        try {
            msgList = new ListDisplayView(announcementPresenter.getChatListTitle(), announcementPresenter.getChatList());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainMenuButtons();
        }
    }

    private void showOpenAnChat() {
        showOpenChat();
        dialogPrompt = new JLabel(announcementPresenter.printChatIdPrompt());

        okayButton.setActionCommand("choose anchat");
    }

    private void showAnnouncementChat() {
        String chatID = inputField.getText();

        try {
            msgList = new ListDisplayView(announcementPresenter.getChatTitle(chatID), announcementPresenter.getChat(chatID));
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
        }
    }

    private void showCreateChat() {
        //dialogPrompt = new JLabel(presenter.printContactUsernamePrompt()); TODO: make this method return string
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        okayButton.setActionCommand("create chat");
        okayButton.setVisible(true);
    }

    private void showCreateGroupChat() {
        //dialogPrompt = new JLabel(presenter.printContactUsernamesPrompt()); TODO: make this method return string
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        okayButton.setActionCommand("create groupchat");
        okayButton.setVisible(true);
    }

    private void createChat() {
        String participantID = inputField.getText();
    }

    private void createGroupChat(){
        String participantIDs = inputField.getText();
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        String eventName = event.getActionCommand();

        if(eventName.equals(continueButton.getActionCommand())) {
            eventName = (String)dropDownMenu.getSelectedItem();
        }

        if(eventName.equals(this.menuOp[6])) {
            hideMainMenuButtons();
            showViewAnnouncementChannels();
        }

        if(eventName.equals(this.menuOp[7])) {
            hideMainMenuButtons();
            showOpenAnChat();
        }

        if(eventName.equals(this.menuOp[8])) {
            hideMainMenuButtons();
            showCreateChat();
        }

        if(eventName.equals(this.menuOp[9])) {
            hideMainMenuButtons();
            showCreateGroupChat();
        }

        if (eventName.equals("show anchat")) {
            showAnnouncementChat();
            showMainMenuButtons();
        }

        if (eventName.equals("create chat")) {
            createChat();
            showMainMenuButtons();
        }

        if (eventName.equals("create groupchat")) {
            createGroupChat();
            showMainMenuButtons();
        }
    }
}
