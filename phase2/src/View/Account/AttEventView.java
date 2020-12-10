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

    JButton signupButton, cancelSpotButton, chooseRoomButton;
    JTextField inputField;
    ListDisplayView eventList;
    JComboBox<String> roomChoice;
    JLabel dialoguePrompt;

    public AttEventView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttEventController) controller;
        this.presenter = ((AttEventController) controller).getPresenter();

        contentPane.setBackground(whiteBG);// Sets background colour to white

        dialoguePrompt = new JLabel("");
        initializeObject(dialoguePrompt);

        signupButton = newButton("Sign up");
        cancelSpotButton = newButton("Cancel spot");
        chooseRoomButton = newButton("Choose room");

        inputField = new JTextField(50);
        initializeObject(inputField);
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

    private void showSignUp() {
        dialoguePrompt.setText(this.presenter.printAddEventPrompt());
        dialoguePrompt.setVisible(true);
        inputField.setVisible(true);
        signupButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showCancelSpot() {
        dialoguePrompt.setText(this.presenter.printRemoveEventPrompt());
        dialoguePrompt.setVisible(true);
        inputField.setVisible(true);
        cancelSpotButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showRoomChoice() {
        dialoguePrompt.setText(this.presenter.printRoomChoicePrompt());
        roomChoice.setVisible(true);
        chooseRoomButton.setVisible(true);
        backButton.setVisible(true);
    }


    /**
     * Uses the controller to try and sign up the user for an event (add event to their event list)
     */
    private void signup() {
        String event = inputField.getText();

        try {
            if (controller.signupForEvent(event)) {
                dialoguePrompt.setText(this.presenter.printEventAdded());
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
        String event = inputField.getText();

        try {
            if (controller.cancelSpotFromEvent(event)) {
                dialoguePrompt.setText(this.presenter.printEventRemoved());
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
