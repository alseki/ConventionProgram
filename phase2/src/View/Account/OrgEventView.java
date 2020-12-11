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
    JButton createRoomButton;
    JTextField inputField1, inputField2, messageField;

    public OrgEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (OrgEventController) controller;
        this.presenter = ((OrgEventController) controller).getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(whiteBG);// Sets background colour

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);

    }


    private void showCreateRoom() {
        backButton.setVisible(true);

        dialogPrompt.setText(presenter.addRoomPrompt());
        dialogPrompt.setVisible(true);

        String roomName = JOptionPane.showInputDialog(null, presenter.roomNamePrompt(),
                "Helloooo", JOptionPane.PLAIN_MESSAGE);

        String roomCap = JOptionPane.showInputDialog(null, presenter.roomCapacityPrompt(),
                "Helloooo", JOptionPane.PLAIN_MESSAGE);

        // try-catch block and more code here
    }

    private void showCreateEvent() {
        backButton.setVisible(true);

        String title = presenter.printCreateEventPrompt();
        dialogPrompt.setText(title);
        dialogPrompt.setVisible(true);

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

    }

    private void showCreateSpeakerAcc() {
        backButton.setVisible(true);

        String title = presenter.printAddSpeakerPrompt();
        dialogPrompt.setText(title);
        dialogPrompt.setVisible(true);

        String speName = JOptionPane.showInputDialog(null, presenter.printAddNamePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String speUser = JOptionPane.showInputDialog(null, presenter.printAddUsernamePrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String spePassword = JOptionPane.showInputDialog(null, presenter.printAddPasswordPrompt(),
                title, JOptionPane.PLAIN_MESSAGE);

        String speEmail = JOptionPane.showInputDialog(null, presenter.printAddEmailPrompt(),
                title, JOptionPane.PLAIN_MESSAGE);
    }

    private void showMakeEventAnnouncement() {
        backButton.setVisible(true);

        dialogPrompt.setText(presenter.printEventMessageIntro());
        dialogPrompt.setVisible(true);

        // some text field here that takes in the announcement
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

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
