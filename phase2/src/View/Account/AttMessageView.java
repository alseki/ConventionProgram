package View.Account;

import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttMessageMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttMessageView extends AccountView{
    AttMessageController controller;
    AttMessageMenu presenter;

    JLabel yourInbox, yourSentBox, yourChats, msgs, inputChatIDMsgPrompt, inputMsgPrompt,
            inputUserPrompt, inputUsersPrompt;
    JButton okayButton, continueButton;
    JTextField inputChatField, inputMsgField, inputUser, inputUsers;

    public AttMessageView(SubMenu controller) {
        super();
        this.controller = (AttMessageController) controller;
        this.presenter = (AttMessageMenu) controller.getPresenter();



        frame = new JFrame(this.presenter.getMenuTitle());// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);

        // contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 100, 200));// Sets background colour
        contentPane.setLayout(new FlowLayout());

        setupEventMenuOptions();

        // general Message Menu setups
        setupCheckInbox();
        setupCheckSentBox();
        setupViewChats();
        setupViewMsgsInChat();
        setupSendMsg();

        // Attendee exclusive set-ups
        setupViewAnnouncementChannels();
        setupViewEventAnnouncements();
        setupCreateChat();
        setupCreateGroupChat();

        showMainDropDownMenu();

        continueButton = newButton("continue");
        contentPane.add(continueButton);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    private void setupEventMenuOptions() {
        makeDropDownMenu(presenter);
    }

    private void setupCheckInbox() {
        yourInbox = new JLabel(this.presenter.getInboxTitle());
        yourInbox.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        yourInbox.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(yourInbox);
        yourInbox.setVisible(false);

        okayButton = newButton("okay");

        // return to AttMsgMenu button
    }

    private void setupCheckSentBox() {
        yourSentBox = new JLabel(this.presenter.getOutboxTitle());
        yourSentBox.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        yourSentBox.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(yourSentBox);
        yourSentBox.setVisible(false);

        // this also has an "okay" button

        // also a "return to AttMsgMenu" button
    }

    private void setupViewChats() {
        yourChats = new JLabel(this.presenter.getChatListTitle());
        yourChats.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        yourChats.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(yourChats);
        yourChats.setVisible(false);

        // also a "return to AttMsgMenu" button
    }

    private void setupViewMsgsInChat() {
        msgs = new JLabel(this.presenter.printChatIdPrompt());
        msgs.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        msgs.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(msgs);
        msgs.setVisible(false);

        inputChatField = new JTextField(40);
        contentPane.add(inputChatField);
        inputChatField.setVisible(false);

        // return button
    }

    private void setupSendMsg() {
        inputChatIDMsgPrompt = new JLabel(this.presenter.printChatIdMessagePrompt());
        inputChatIDMsgPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputChatIDMsgPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputChatIDMsgPrompt);
        inputChatIDMsgPrompt.setVisible(false);

        // inputChatField also used here
        //inputChatField.setVisible(false);

        inputMsgPrompt = new JLabel(this.presenter.printChatIdMessagePrompt());
        inputMsgPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputMsgPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputMsgPrompt);
        inputMsgPrompt.setVisible(false);

        inputMsgField = new JTextField(100);
        contentPane.add(inputMsgField);
        inputMsgField.setVisible(false);
    }

    private void setupViewAnnouncementChannels() {
        // already set up in setupSendMsg()

        /*
        inputChatIDMsgPrompt = new JLabel(this.presenter.getChatListTitle());
        inputChatIDMsgPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputChatIDMsgPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputChatIDMsgPrompt);
        inputChatIDMsgPrompt.setVisible(false);
         */

    }

    private void setupViewEventAnnouncements() {
        // already set up in setupSentMsg

        /*
        inputChatIDMsgPrompt = new JLabel();
        inputChatIDMsgPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputChatIDMsgPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputChatIDMsgPrompt);
        inputChatIDMsgPrompt.setVisible(false);
         */

        //input chat field used here too
    }

    private void setupCreateChat() {
        //FIXME something's up with this JLabel constructor
        //inputUserPrompt = new JLabel(this.presenter.printContactUsernamePrompt());
        inputUserPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputUserPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputUserPrompt);
        inputUserPrompt.setVisible(false);

        inputUser = new JTextField(100);
        contentPane.add(inputUser);
        inputUser.setVisible(false);
    }

    private void setupCreateGroupChat() {
        //FIXME same issue as above
        //inputUsersPrompt = new JLabel(this.presenter.printContactUsernamesPrompt());
        inputUsersPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        inputUsersPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(inputUsersPrompt);
        inputUsersPrompt.setVisible(false);

        inputUsers = new JTextField(100);
        contentPane.add(inputUsers);
        inputUsers.setVisible(false);
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
        yourInbox.setVisible(true);
        okayButton.setVisible(true);
    }

    private void showCheckSentBox() {
        yourSentBox.setVisible(true);
        okayButton.setVisible(true);
    }

    private void showViewChats() {
        yourChats.setVisible(true);
        okayButton.setVisible(true);
    }

    private void showViewMsgsInChat() {
        msgs.setVisible(true);
        inputChatField.setVisible(true);
        //okayButton.setVisible(true);
    }

    private void showSendMsg() {
        inputChatIDMsgPrompt.setVisible(true);
        inputChatField.setVisible(true);
    }

    private void showViewAnnouncementChannels() {
        inputChatIDMsgPrompt.setVisible(true);
    }

    private void showViewEventAnnouncements() {
        inputChatIDMsgPrompt.setVisible(true);
        inputChatField.setVisible(true);
    }

    private void showCreateChat() {
        inputUserPrompt.setVisible(true);
        inputUser.setVisible(true);
    }

    private void showCreateGroupChat() {
        inputUsersPrompt.setVisible(true);
        inputUsers.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

        if(eventName.equals(continueButton.getActionCommand())) {
            eventName = (String)dropDownMenu.getSelectedItem();
        }

        if(eventName.equals(presenter.getMenuOptions()[1])) {
            hideMainDropDownMenu();
            showCheckInbox();
        }

        if(eventName.equals(presenter.getMenuOptions()[2])) {
            hideMainDropDownMenu();
            showCheckSentBox();
        }

        if(eventName.equals(presenter.getMenuOptions()[3])) {
            hideMainDropDownMenu();
            showViewChats();
        }

        if(eventName.equals(presenter.getMenuOptions()[4])) {
            hideMainDropDownMenu();
            showViewMsgsInChat();
        }

        if(eventName.equals(presenter.getMenuOptions()[5])) {
            hideMainDropDownMenu();
            showSendMsg();
        }

        if(eventName.equals(presenter.getMenuOptions()[6])) {
            hideMainDropDownMenu();
            showViewAnnouncementChannels();
        }

        if(eventName.equals(presenter.getMenuOptions()[7])) {
            hideMainDropDownMenu();
            showViewEventAnnouncements();
        }

        if(eventName.equals(presenter.getMenuOptions()[8])) {
            hideMainDropDownMenu();
            showCreateChat();
        }

        if(eventName.equals(presenter.getMenuOptions()[9])) {
            hideMainDropDownMenu();
            showCreateGroupChat();
        }
    }
}
