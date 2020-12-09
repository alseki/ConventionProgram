package View.Account;

import Presenter.Central.SubMenu;
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
    JLabel dialogPrompt;
    JButton continueButton, okayButton, backButton, signupButton, cancelSpotButton;
    // JTextField inputRoomName, inputRoomCap, inputSName, inputSUsername, inputSPassword, inputSEmail;
    JTextField inputField, messageField;

    public OrgEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (OrgEventController) controller;
        this.presenter = ((OrgEventController) controller).getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(whiteBG);// Sets background colour
        continueButton = newButton("continue");
        contentPane.add(continueButton);

        backButton = newButton("back");
        contentPane.add(backButton);

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);
        inputField = new JTextField(100);
        initializeObject(inputField);
        messageField = new JTextField(100);
        initializeObject(messageField);
        continueButton = newButton("continue");
        initializeObject(continueButton);

        makeDropDownMenu(this.presenter);
        //setupCreateRoom();
        //setupCreateEvent();
        //setupCreateSpeakerAcc();
        //setupMakeEventAnnouncement();
    }

    /*
    private void setupCreateRoom() {
        addRoomPrompt = new JLabel(this.presenter.addRoomPrompt());
        addRoomPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addRoomPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addRoomPrompt);
        addRoomPrompt.setVisible(false);

        roomNamePrompt = new JLabel(this.presenter.roomNamePrompt());
        roomNamePrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        roomNamePrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(roomNamePrompt);
        roomNamePrompt.setVisible(false);

        inputRoomName = new JTextField(40);
        contentPane.add(inputRoomName);
        inputRoomName.setVisible(false);

        roomCapPrompt = new JLabel(this.presenter.roomCapacityPrompt());
        roomCapPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        roomCapPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(roomCapPrompt);
        roomCapPrompt.setVisible(false);

        inputRoomCap = new JTextField(40);
        contentPane.add(inputRoomCap);
        inputRoomCap.setVisible(false);

        okayButton = newButton("Create Room");

        // return to AttEventMenu button
    }

    private void setupCreateEvent() {

        // also a "return to AttEventMenu" button
    }

    private void setupCreateSpeakerAcc() {
        addSpeakerPrompt = new JLabel(this.presenter.printAddSpeakerPrompt());
        addSpeakerPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addSpeakerPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addSpeakerPrompt);
        addSpeakerPrompt.setVisible(false);

        addSNamePrompt = new JLabel(this.presenter.printAddNamePrompt());
        addSNamePrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addSNamePrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addSNamePrompt);
        addSNamePrompt.setVisible(false);

        inputSName = new JTextField(40);
        contentPane.add(inputSName);
        inputSName.setVisible(false);

        addSUsernamePrompt = new JLabel(this.presenter.printAddPasswordPrompt());
        addSUsernamePrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addSUsernamePrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addSUsernamePrompt);
        addSUsernamePrompt.setVisible(false);

        inputSUsername = new JTextField(40);
        contentPane.add(inputSUsername);
        inputSUsername.setVisible(false);

        addSPasswordPrompt = new JLabel(this.presenter.printAddPasswordPrompt());
        addSPasswordPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addSPasswordPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addSPasswordPrompt);
        addSPasswordPrompt.setVisible(false);

        inputSPassword = new JTextField(40);
        contentPane.add(inputSPassword);
        inputSPassword.setVisible(false);

        addSEmailPrompt = new JLabel(this.presenter.printAddPasswordPrompt());
        addSEmailPrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        addSEmailPrompt.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(addSEmailPrompt);
        addSEmailPrompt.setVisible(false);

        inputSEmail = new JTextField(40);
        contentPane.add(inputSEmail);
        inputSEmail.setVisible(false);

        okayButton = newButton("Create Room");

        // and maybe also a "return to AttEventMenu" button
    }

    //TODO: this method
    private void setupMakeEventAnnouncement() {
    }
     */


    private void showCreateRoom() {
        dialogPrompt.setText(this.presenter.addRoomPrompt());

        //addRoomPrompt.setVisible(true);
        //roomNamePrompt.setVisible(true);
        //inputRoomName.setVisible(true);
        //roomCapPrompt.setVisible(true);
        //inputRoomCap.setVisible(true);
    }

    private void showCreateEvent() {

    }

    private void showCreateSpeakerAcc() {

    }

    private void showMakeEventAnnouncement() {

    }



    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();

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
