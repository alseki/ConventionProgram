package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.OrganizerController.OrgEventController;
import Presenter.OrganizerController.OrgEventMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OrgEventView extends AccountView {
    OrgEventController controller;
    OrgEventMenu presenter;
    String[] menuOp;

    // JLabel addRoomPrompt, roomNamePrompt, roomCapPrompt, addSpeakerPrompt, addSNamePrompt,
    //         addSUsernamePrompt, addSPasswordPrompt, addSEmailPrompt;
    JLabel dpMain, dp1, dp2, dp3, dp4, dp5, dp6, dp7; // dp = "dialogue prompt"
    JButton createRoomButton, createEventButton;
    JTextField input1, input2, input3, input4, input5, input6, input7;
    JComboBox<String> allEvents;

    /**
     * The view for organizer users to see their convention event options.
     * @param controller OrgEventController for handling user input
     */
    public OrgEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (OrgEventController) controller;
        this.presenter = (OrgEventMenu) controller.getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(new Color(200, 10, 150));

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
        createEventButton = newButton("Create Event");
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

        dpMain.setText(presenter.printCreateEventPrompt());
        dpMain.setVisible(true);

        dp1.setText(presenter.printEventTypePrompt());
        dp1.setVisible(true);
        input1.setVisible(true);

            //TODO: display a list event types when user types in 0?

        dp2.setText(presenter.printRoomNamePrompt());
        dp2.setVisible(true);
        input2.setVisible(true);

            //TODO: display a list of room names when user types in 0?

        dp3.setText(presenter.printEventNamePrompt());
        dp3.setVisible(true);
        input3.setVisible(true);

        dp4.setText(presenter.printDescriptionPrompt());
        dp4.setVisible(true);
        input4.setVisible(true);

        dp5.setText(presenter.printStartTimePrompt());
        dp5.setVisible(true);
        input5.setVisible(true);

        dp6.setText(presenter.printEndTimePrompt());
        dp6.setVisible(true);
        input6.setVisible(true);

        dp7.setText(presenter.printSpeakerUsernamePrompt());
        dp7.setVisible(true);
        input7.setVisible(true);

        //dp8.setText(presenter.printChatNamePrompt());
        //dp8.setVisible(true);
        //input8.setVisible(true);

        createEventButton.setVisible(true);

        /*
        String eventType = JOptionPane.showInputDialog(null, presenter.printEventTypePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String eventRoom = JOptionPane.showInputDialog(null, presenter.printRoomNamePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String eventName = JOptionPane.showInputDialog(null, presenter.printEventNamePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String eventChat = JOptionPane.showInputDialog(null, presenter.printChatNamePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String eventDesc = JOptionPane.showInputDialog(null, presenter.printDescriptionPrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String eventTime = JOptionPane.showInputDialog(null, presenter.printStartTimePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

         */

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
            showMakeEventAnnouncement();
        }
    }
}
