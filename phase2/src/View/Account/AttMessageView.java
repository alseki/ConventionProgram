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
    JButton createChatButton;
    JLabel dialogPrompt;

    public AttMessageView(SubMenu controller) {
        super(controller);
        this.controller = (AttMessageController) controller;
        this.presenter = ((AttMessageController) controller).getPresenter();
        this.announcementPresenter = ((AttMessageController) controller).getAnChatPresenter();

        contentPane.setBackground(pinkBG);// Sets background colour

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);

        createChatButton = newButton("Create Chat!");
    }

    private void showViewAnnouncementChannels() {
        try {
            msgList = new ListDisplayView(announcementPresenter.getChatListTitle(),
                    announcementPresenter.getChatList());
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
            showMainDropDownMenu();
        }
    }

    private void showOpenAnChat() {
        showOpenChat();
        dialogPrompt.setText(announcementPresenter.printChatIdPrompt());

        okayButton.setActionCommand("choose anchat");
    }

    private void showAnnouncementChat() {
        String chatID = inputField.getText();

        try {
            msgList = new ListDisplayView(announcementPresenter.getChatTitle(chatID),
                    announcementPresenter.getChat(chatID));
            showMainDropDownMenu();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), e.getMessage());
        }
    }

    private void showCreateChat() {
        dialogPrompt.setText(presenter.printContactUsernamePrompt());
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        createChatButton.setText("Create Chat");
        createChatButton.setActionCommand("Create Chat");
        createChatButton.setVisible(true);
    }

    private void showCreateGroupChat() {
        dialogPrompt.setText(presenter.printContactUsernamesPrompt());
        dialogPrompt.setVisible(true);

        new JTextField(100);
        inputField.setVisible(true);

        createChatButton.setText("Create Group Chat");
        createChatButton.setActionCommand("Create Group Chat");
        createChatButton.setVisible(true);
    }

    private void createChat() {
        String participantID = inputField.getText();

        /*
        try {
            label = new JLabel(this.controller.createChat(participantID));

        } catch(InvalidChoiceException e) {


        }

         */

        JOptionPane.showConfirmDialog(null, "Chat Creation Successful",
                "Message", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

    }

    private void createGroupChat(){
        String participantIDs = inputField.getText();
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

        if (eventName.equals("show anchat")) {
            showAnnouncementChat();
            showMainDropDownMenu();
        }

        if (eventName.equals("Create Chat")) {
            createChat();
            showMainDropDownMenu();
        }

        if(eventName.equals("Create Group Chat")) {
            createGroupChat();
            showMainDropDownMenu();
        }
    }
}
