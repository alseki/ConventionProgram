package Presenter.AttendeeController;

// Programmers: Cara McNeil, Allen Kim, Eytan Weinstein
// Description: All the methods that take user input in the AttendeeController Event Menu
// Date Created: 01/11/2020
// Date Modified: 09/12/2020

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Central.SubMenu;
import Event.CapacityException;
import Event.EventPermissions;
import Person.AttendeeManager;

import java.util.Scanner;

public class AttEventController extends SubMenu {

    private final String currentUserID;
    private final AttendeeManager attendeeManager;
    private final EventPermissions eventPermissions;
    private final AttEventMenu presenter;

    public AttEventController(SubMenu subMenu, String currentUserID, AttendeeManager attendeeManager) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.attendeeManager = attendeeManager;
        eventPermissions = new EventPermissions(roomManager, eventManager);
        presenter = new AttEventMenu(currentUserID, roomManager, eventManager, attendeeManager);
    }

    /**
     * Getter for currentUserID
     * @return currentUserID
     */
    public String getCurrentUserID() {
        return currentUserID;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    public void menuChoice() {
        do {
            switch(1) {
                case 1:
                    // TODO reformat see event list option
                    /*try {
                        String roomID = this.getRoomChoice();
                        if (roomID.equals("1")) {
                            presenter.printEventList();
                        } else {
                            presenter.printRoomEventList(roomManager.getEventIDs(roomID),
                                    roomManager.getRoomName(roomID));
                        }
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }*/
                    break;
                case 4:
                    // TODO figure out how to print AttendeeEventList
                    //presenter.printAttendeeEventList();
                    break;
            }
        }
        while (true);

    }

    /**
     * Takes user input to pick a Room for which that user wishes to view the Events held there
     * @return the ID of the Room chosen
     */
   /* private String getRoomChoice() throws InvalidChoiceException {
        presenter.printRoomChoicePrompt();
        String room = "";//SubMenu.readInput(input);
        if (room.equals("0")) {
            presenter.printRoomList();
            room = "";//SubMenu.readInput(input);
        }
        if (room.equals("1")) {
            return "1";
        }
        if (roomManager.getRoomID(room) != null) {
            return roomManager.getRoomID(room);
        } else {
            throw new InvalidChoiceException("room");
        }
    }

    /**
     * Tries to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     */
    public boolean signupForEvent(String eventName) throws InvalidChoiceException, CapacityException  {
        String event = eventManager.getEventID(eventName);
        if (event == null) {
            throw new InvalidChoiceException("event");
        }
        eventPermissions.signAttendeeUpForEvent(currentUserID, event);
        boolean eventAddedToPerson = attendeeManager.signUpForEvent(currentUserID, event);
        attendeeManager.addAnChat(currentUserID, eventManager.getEventChat(event));
        return eventAddedToPerson;
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     */
    public boolean cancelSpotFromEvent(String eventName) throws InvalidChoiceException {
        String event = eventManager.getEventID(eventName);
        if (event == null) {
            throw new InvalidChoiceException("event");
        }

        boolean personRemovedFromEvent = eventPermissions.removeAttendeeFromEvent(currentUserID, event);
        boolean eventRemovedFromPerson = attendeeManager.removeSpotFromEvents(currentUserID, event);
        attendeeManager.removeAnChat(currentUserID, eventManager.getEventChat(event));
        return personRemovedFromEvent && eventRemovedFromPerson;
    }

    @Override
    public AttEventMenu getPresenter() {
        return this.presenter;
    }
}
