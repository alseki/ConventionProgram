package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Presenter.OrganizerController.OrgEventController;
import Presenter.OrganizerController.OrgEventMenu;
import View.AccountHelpers.ListDisplayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OrgEventView extends AccountView {
    OrgEventController controller;
    OrgEventMenu presenter;
    String[] menuOp;

    // JLabel addRoomPrompt, roomNamePrompt, roomCapPrompt, addSpeakerPrompt, addSNamePrompt,
    //         addSUsernamePrompt, addSPasswordPrompt, addSEmailPrompt;
    JLabel dpMain, dp1, dp2, dp3, dp4, dp5, dp6, dp7, dp8; // dp = "dialogue prompt"
    JButton createRoomButton, createEventButton, makeAnnouncementButton;
    JTextField input1, input2, input3, input4, input5, input6, input7, input8;
    JComboBox<String> allEvents;
    ListDisplayView allEventTypes, allRooms;
    JTextArea messageField;

    /**
     * The view for organizer users to see their convention event options.
     * @param controller OrgEventController for handling user input
     */
    public OrgEventView(SubMenu controller) {
        super(controller);
        this.controller = (OrgEventController) controller;
        this.presenter = (OrgEventMenu) controller.getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(new Color(200, 10, 150));

        createRoomButton = newButton("Create room");
        createEventButton = newButton("Create Event");
        makeAnnouncementButton = newButton("Make Announcement");

        dpMain = new JLabel("");
        initializeObject(dpMain);

        dp1 = new JLabel("");
        initializeObject(dp1);
        input1 = new JTextField(20);
        initializeObject(input1);

        dp2 = new JLabel("");
        initializeObject(dp2);
        input2 = new JTextField(20);
        initializeObject(input2);

        dp3 = new JLabel("");
        initializeObject(dp3);
        input3 = new JTextField(20);
        initializeObject(input3);

        dp4 = new JLabel("");
        initializeObject(dp4);
        input4 = new JTextField(20);
        initializeObject(input4);

        dp5 = new JLabel("");
        initializeObject(dp5);
        input5 = new JTextField(20);
        initializeObject(input5);

        dp6 = new JLabel("");
        initializeObject(dp6);
        input6 = new JTextField(20);
        initializeObject(input6);

        dp7 = new JLabel("");
        initializeObject(dp7);
        input7 = new JTextField(20);
        initializeObject(input7);

        dp8 = new JLabel("");
        initializeObject(dp8);
        input8 = new JTextField(20);
        initializeObject(input8);

        messageField = new JTextArea(5, 20);
        messageField.setPreferredSize(new Dimension(20, 20));
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        initializeObject(messageField);
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
        String roomCap = input2.getText();

        try {
            if(controller.addRoom(roomName, Integer.parseInt(roomCap))) {
                JOptionPane.showConfirmDialog(null, "Room creation successful!",
                        "Success", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, "Please enter valid information.",
                        "Warning!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            }
        } catch (InvalidChoiceException c) {
            exceptionDialogBox(presenter.printException(c));
        }
    }

    private void showCreateEvent() {
        backButton.setVisible(true);

        dpMain.setText(presenter.printCreateEventPrompt());
        dpMain.setVisible(true);

        dp1.setText(presenter.printEventNamePrompt());
        dp1.setVisible(true);
        input1.setVisible(true);

        dp2.setText(presenter.printEventTypePrompt());
        dp2.setVisible(true);
        input2.setVisible(true);

        dp3.setText(presenter.printEventCapPrompt());
        dp3.setVisible(true);
        input3.setVisible(true);

        dp4.setText(presenter.printRoomNamePrompt());
        dp4.setVisible(true);
        input4.setVisible(true);

        dp5.setText(presenter.printSpeakerUsernamePrompt());
        dp5.setVisible(true);
        input5.setVisible(true);

        dp6.setText(presenter.printStartTimePrompt());
        dp6.setVisible(true);
        input6.setVisible(true);

        dp7.setText(presenter.printEndTimePrompt());
        dp7.setVisible(true);
        input7.setVisible(true);

        dp8.setText(presenter.printDescriptionPrompt());
        dp8.setVisible(true);
        input8.setVisible(true);

        //dp8.setText(presenter.printChatNamePrompt());
        //dp8.setVisible(true);
        //input8.setVisible(true);

        createEventButton.setVisible(true);
    }

    private void createEvent() {
        String nameOfEvent = input1.getText();
        String eventType = input2.getText();
        String eventCap = input3.getText();
        String roomName = input4.getText();
        String speakerUsername = input5.getText();
        String startTime = input6.getText();
        String endTime = input7.getText();
        String eventDesc = input8.getText();

        try {
            if(eventType.equals("0")) {
                allEventTypes = new ListDisplayView("All Event Types", presenter.eventTypes(eventType));
            } else if (roomName.equals("0")) {
                try {
                    allRooms = new ListDisplayView("All Rooms", presenter.getRoomList());
                } catch (NoDataException d) {
                    exceptionDialogBox(presenter.printException(d));
                }
            } else if(eventType.equals("") && roomName.equals("")) {
                JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), "Please enter information carefully",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            } else if(controller.createEvent(nameOfEvent, speakerUsername, startTime, endTime, eventDesc, Integer.parseInt(eventCap),
                    controller.getEventType(eventType), roomName)){
                JOptionPane.showConfirmDialog(null, "Success", "Event has been created!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), "Please enter information carefully",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            }
        } catch (InvalidChoiceException c) {
            exceptionDialogBox(presenter.printException(c));
        }
    }

    private void showMakeEventAnnouncement() {
        backButton.setVisible(true);

        dpMain.setText(presenter.printEventMessageIntro());
        dpMain.setVisible(true);

        dp1.setText(presenter.printEventNamePrompt());
        dp1.setVisible(true);
        input1.setVisible(true);

        dp2.setText(presenter.printChatNamePrompt());
        dp2.setVisible(true);
        input2.setVisible(true);

        dp3.setText(presenter.printMessageContentPrompt());
        dp3.setVisible(true);
        messageField.setVisible(true);

        makeAnnouncementButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(createRoomButton.getActionCommand())) {
            createRoom();
        }

        if(eventName.equals(createEventButton.getActionCommand())) {
            createEvent();
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
