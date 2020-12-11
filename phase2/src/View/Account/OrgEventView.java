package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.OrganizerController.OrgEventController;
import Presenter.OrganizerController.OrgEventMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class OrgEventView extends AccountView {
    OrgEventController controller;
    OrgEventMenu presenter;
    String[] menuOp;

    // JLabel addRoomPrompt, roomNamePrompt, roomCapPrompt, addSpeakerPrompt, addSNamePrompt,
    //         addSUsernamePrompt, addSPasswordPrompt, addSEmailPrompt;
    JLabel dpMain, dp1, dp2, dp3; // dp = "dialogue prompt"
    JButton createRoomButton;
    JTextField input1, input2;

    /**
     * The view for organizer users to see their convention event options.
     * @param controller OrgEventController for handling user input
     */
    public OrgEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (OrgEventController) controller;
        this.presenter = (OrgEventMenu) controller.getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(whiteBG);// Sets background colour

        dpMain = new JLabel("");
        initializeObject(dpMain);

        dp1 = new JLabel("");
        initializeObject(dp1);
        input1 = new JTextField(50);
        initializeObject(input1);

        dp2 = new JLabel("");
        initializeObject(dp2);
        input2 = new JTextField(50);
        initializeObject(input2);

        createRoomButton = newButton("Create room");
    }


    private void showCreateRoom() {
        backButton.setVisible(true);

        dpMain.setText(presenter.addRoomPrompt());
        dpMain.setVisible(true);

        dp1.setText(presenter.roomNamePrompt());
        dp1.setVisible(true);
        input1.setVisible(true);

        dp2.setText(presenter.roomCapacityPrompt());
        dp2.setVisible(true);
        input2.setVisible(true);

        createRoomButton.setVisible(true);
    }

    private void createRoom() {
        String roomName = input1.getText();
        int roomCap = Integer.parseInt(input2.getText());

        try { //FIXME this method should be fine but the Controller method it calls is the issue aaaaaaa

            if(controller.addRoom(roomName, roomCap)) {
                JOptionPane.showConfirmDialog(null, "Room creation successful!",
                        "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, "Please enter valid information.",
                        "Warning!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            }
        } catch (InvalidChoiceException c) {
            exceptionDialogBox(presenter.exceptionTitle(), c.getMessage());
        }
    }

    private void showCreateEvent() {
        backButton.setVisible(true);

        dp1.setText(presenter.printCreateEventPrompt());
        dp1.setVisible(true);

        String eventType = JOptionPane.showInputDialog(null, presenter.printEventTypePrompt(),
                "Type", JOptionPane.PLAIN_MESSAGE);

        String eventRoom = JOptionPane.showInputDialog(null, presenter.printRoomNamePrompt(),
                "Room", JOptionPane.PLAIN_MESSAGE);

        String eventName = JOptionPane.showInputDialog(null, presenter.printEventNamePrompt(),
                "Name", JOptionPane.PLAIN_MESSAGE);

        String eventChat = JOptionPane.showInputDialog(null, presenter.printChatNamePrompt(),
                "Chat", JOptionPane.PLAIN_MESSAGE);

        String eventDesc = JOptionPane.showInputDialog(null, presenter.printDescriptionPrompt(),
                "Description", JOptionPane.PLAIN_MESSAGE);

        String eventTime = JOptionPane.showInputDialog(null, presenter.printStartTimePrompt(),
                "Start time", JOptionPane.PLAIN_MESSAGE);

    }

    private void showCreateSpeakerAcc() {
        backButton.setVisible(true);

        dp1.setText(presenter.printAddSpeakerPrompt());
        dp1.setVisible(true);

        String speName = JOptionPane.showInputDialog(null, presenter.printAddNamePrompt(),
                "Full name", JOptionPane.PLAIN_MESSAGE);

        String speUser = JOptionPane.showInputDialog(null, presenter.printAddUsernamePrompt(),
                "Username", JOptionPane.PLAIN_MESSAGE);

        String spePassword = JOptionPane.showInputDialog(null, presenter.printAddPasswordPrompt(),
                "Password", JOptionPane.PLAIN_MESSAGE);

        String speEmail = JOptionPane.showInputDialog(null, presenter.printAddEmailPrompt(),
                "Email", JOptionPane.PLAIN_MESSAGE);
    }

    private void showMakeEventAnnouncement() {
        backButton.setVisible(true);

        dp1.setText(presenter.printEventMessageIntro());
        dp1.setVisible(true);

        // some text field here that takes in the announcement
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(createRoomButton.getActionCommand())) {
            createRoom();
        }

        if(eventName.equals(menuOp[0])) {
            hideMainDropDownMenu();
            showCreateRoom();
        }

        if(eventName.equals(menuOp[1])) {
            hideMainDropDownMenu();
            showCreateEvent();
        }

        if(eventName.equals(menuOp[2])) {
            hideMainDropDownMenu();
            showCreateSpeakerAcc();
        }

        if(eventName.equals(menuOp[3])) {
            hideMainDropDownMenu();
            showMakeEventAnnouncement();
        }
    }
}
