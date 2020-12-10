package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttEventMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Event.CapacityException;
import Presenter.Exceptions.NoDataException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class AttEventView extends AccountView {
    AttEventController controller;
    AttEventMenu presenter;

    JLabel dialogueMsg;
    JButton signupButton, cancelSpotButton, chooseRoomButton;
    JTextField inputEventName;
    ListDisplayView eventList;
    JComboBox<String> roomChoice;

    public AttEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttEventController) controller;
        this.presenter = ((AttEventController) controller).getPresenter();

        contentPane.setBackground(whiteBG);// Sets background colour to white

        setupSignUpEvent();
        setupCancelFromEvent();
        setupRooms();
    }

    private void setupRooms() {
        try {
            roomChoice = new JComboBox<>(presenter.getRoomList());
        } catch (NoDataException e) {
            String[] rooms = {"See all events"};
            roomChoice = new JComboBox<>(rooms);
        } finally {
            initializeObject(roomChoice);

            chooseRoomButton = newButton("see events");
            initializeObject(chooseRoomButton);
        }
    }

    private void setupSignUpEvent() {
        dialogueMsg = new JLabel();
        contentPane.add(dialogueMsg);
        dialogueMsg.setVisible(false);

        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        signupButton = newButton("Signup");
        initializeObject(signupButton);

        // also a "return to AttEventMenu" button
    }

    private void setupCancelFromEvent() {
        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        cancelSpotButton = newButton("Cancel Spot");
        initializeObject(cancelSpotButton);

        // and maybe also a "return to AttEventMenu" button
    }

    private void showSignUp() {
        dialogueMsg = new JLabel(this.presenter.printAddEventPrompt());
        dialogueMsg.setVisible(true);
        inputEventName.setVisible(true);
        signupButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showCancelSpot() {
        dialogueMsg = new JLabel(this.presenter.printRemoveEventPrompt());
        dialogueMsg.setVisible(true);
        inputEventName.setVisible(true);
        cancelSpotButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showRoomChoice() {
        dialogueMsg = new JLabel(this.presenter.printRoomChoicePrompt());
        roomChoice.setVisible(true);
        chooseRoomButton.setVisible(true);
        backButton.setVisible(true);
    }


    /**
     * Uses the controller to try and sign up the user for an event (add event to their event list)
     */
    private void signup() {
        String event = inputEventName.getText();

        try {
            if (controller.signupForEvent(event)) {
                dialogueMsg = new JLabel(this.presenter.printEventAdded());
            }
            else {
                JOptionPane.showConfirmDialog(null, presenter.exceptionTitle(), "Unexpected Error",
                        JOptionPane.DEFAULT_OPTION);
            }

        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        } catch (CapacityException c) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printEventFull());
        }
    }

    /**
     * Uses the controller to try and cancel the user's spot in an event (add event to their event list)
     */
    private void cancelSpot() {
        String event = inputEventName.getText();

        try {
            if (controller.cancelSpotFromEvent(event)) {
                dialogueMsg = new JLabel(this.presenter.printEventRemoved());
            }
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        }
    }

    /**
     * Opens up a new window showing
     */
    private void viewEventList(String room) {
        try {
            if (room.equals("See all events")) {
                eventList = new ListDisplayView(presenter.getEventListTitle(), presenter.printAllEvents());
            } else {
                eventList = new ListDisplayView(presenter.getEventListTitle(), presenter.printEventsInRoom(room));
            }
            eventList.display();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        } finally {
            showMainDropDownMenu();
        }
    }

    /**
     * Opens up a new window showing
     */
    private void viewOwnEvents() {
        try {
            eventList = new ListDisplayView(presenter.ownEventListTitle(), presenter.getOwnEventList());
            eventList.display();
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        } finally {
            showMainDropDownMenu();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(signupButton.getActionCommand())) {
            signup();
        }

        if(eventName.equals(cancelSpotButton.getActionCommand())) {
            cancelSpot();
        }

        if(eventName.equals(chooseRoomButton.getActionCommand())) {
            viewEventList((String) Objects.requireNonNull(roomChoice.getSelectedItem()));
        }

        if(eventName.equals(menuOp[0])) { // view list of all Events
            hideMainDropDownMenu();
            showRoomChoice();
        }

        if(eventName.equals(menuOp[1])) { // sign up for an Event
            hideMainDropDownMenu();
            showSignUp();
        }

        if(eventName.equals(menuOp[2])) { // cancel your spot from an Event
            hideMainDropDownMenu();
            showCancelSpot();
        }

        if(eventName.equals(menuOp[3])) { // get list of your signed up events
            hideMainDropDownMenu();
            viewOwnEvents();
        }
    }
}
