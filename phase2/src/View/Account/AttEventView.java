package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttEventMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Event.CapacityException;
import Presenter.Exceptions.NoDataException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttEventView extends AccountView {
    AttEventController controller;
    AttEventMenu presenter;

    JLabel dialogueMsg;
    JButton continueButton, okayButton, backButton, signupButton, cancelSpotButton, chooseRoomButton;
    JTextField inputEventName;
    ListDisplayView eventList;

    JComboBox<String> roomChoice;

    public AttEventView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
        this.presenter = ((AttEventController) controller).getPresenter();


        frame = new JFrame(this.presenter.getMenuTitle());// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 200));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        continueButton = newButton("continue");
        contentPane.add(continueButton);

        backButton = newButton("back");
        contentPane.add(backButton);

        okayButton = newButton("okay");
        contentPane.add(okayButton);

        setupEventMenuOptions();
        setupSignUpEvent();
        setupCancelFromEvent();
        setupRooms();

        showMainDropDownMenu();

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    private void setupEventMenuOptions() {
        makeDropDownMenu(presenter);
    }

    private void setupRooms() {
        try {
            roomChoice = new JComboBox<>(presenter.getRoomList());
        } catch (NoDataException e) {
            String[] rooms = {"See all events"};
            roomChoice = new JComboBox<>(rooms);
        } finally {
            contentPane.add(roomChoice);

            chooseRoomButton = newButton("see events");
            contentPane.add(chooseRoomButton);
        }
    }

    private void setupSignUpEvent() {
        dialogueMsg = new JLabel();
        contentPane.add(dialogueMsg);
        dialogueMsg.setVisible(false);

        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        signupButton = newButton("signup");

        // also a "return to AttEventMenu" button
    }

    private void setupCancelFromEvent() {
        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        cancelSpotButton = newButton("cancelSpot");

        // and maybe also a "return to AttEventMenu" button
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
            if (room == "See all events") {
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
        String eventName = event.getActionCommand();


        // if statement for when continue button is pressed


        if(eventName.equals(backButton.getActionCommand()) ||
                eventName.equals(okayButton.getActionCommand())) {
            showMainDropDownMenu();
        }

        if(eventName.equals(signupButton.getActionCommand())) {
            signup();
        }

        if(eventName.equals(cancelSpotButton.getActionCommand())) {
            cancelSpot();
        }

        if(eventName.equals(chooseRoomButton.getActionCommand())) {
            viewEventList((String)roomChoice.getSelectedItem());
        }

        if(eventName.equals("continue")) {
            eventName = (String)dropDownMenu.getSelectedItem();
        }

        if(eventName.equals(presenter.getMenuOptions()[0])) { // return to Att main menu
            showMainDropDownMenu();
        }

        if(eventName.equals(presenter.getMenuOptions()[1])) { // view list of all Events
            hideMainDropDownMenu();
            showRoomChoice();
        }

        if(eventName.equals(presenter.getMenuOptions()[2])) { // sign up for an Event
            hideMainDropDownMenu();
            showSignUp();
        }

        if(eventName.equals(presenter.getMenuOptions()[3])) { // cancel your spot from an Event
            hideMainDropDownMenu();
            showCancelSpot();
        }

        if(eventName.equals(presenter.getMenuOptions()[4])) { // get list of your signed up events
            hideMainDropDownMenu();
            viewOwnEvents();
        }
    }
}
//ignore this comment... testing
