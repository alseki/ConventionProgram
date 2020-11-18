package Controllers;

// Programmers: Cara McNeil, Allen Kim, Eytan Weinstein
// Description: All the methods that take user input in the Attendee Event Menu
// Date Created: 01/11/2020
// Date Modified: 17/11/2020

import java.util.ArrayList;

import Events.EventManager;
import Events.RoomManager;
import Person.AttendeeManager;
import Presenter.AttEventMenu;

import java.util.Scanner;

public class AttEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private AttendeeManager attendeeManager;
    private RoomManager roomManager;
    private AttEventMenu presenter = new AttEventMenu();
    Scanner input = new Scanner(System.in);

    public AttEventController(String currentUserID, AttendeeManager attendeeManager, RoomManager roomManager) {
        this.currentUserID = currentUserID;
        this.attendeeManager = attendeeManager;
        this.roomManager = roomManager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff int was inputted and currentRequest was updated
     */
    @Override
    public boolean menuOptions() {
        presenter.printMenuOptions();
        // TODO update presenter class with a print statement for each option
        currentRequest = input.nextInt();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            switch(currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1:
                    getConventionEventList();
                    break;
                case 2:
                    presenter.printAddEventPrompt();
                    String addingEventInput = input.nextLine();
                    signupForEvent(addingEventInput);
                    break;
                case 3:
                    presenter.printRemoveEventPrompt();
                    String removingEventInput = input.nextLine();
                    cancelSpotFromEvent(removingEventInput);
                    break;
                case 4:
                    getUserEventList();
                    break;
            }
            // TODO add switch statement to call the methods that correspond with currentRequest
        }
        while (currentRequest != 0);
        return true;
    }

    // TODO change, delete and/or add to the methods below

    /**
     * Get's the list of Events happening at the convention
     * @return true iff a formatted list of Events was displayed
     */
    public void getConventionEventList() {
        String roomID = this.getRoomChoice();
        presenter.printRoomEventList(roomManager.getRoom(roomID).getEventList(), roomManager.getRoomName(roomID));
    }

    /**
     * Takes user input to pick a Room for which that user wishes to view the Events held there
     * @return the ID of the Room chosen
     */
    private String getRoomChoice() {
        presenter.printRoomChoicePrompt();
        String room = input.nextLine();
        if (room.equals("0")) {
            presenter.printList(roomManager.getRoomNames());
            room = input.nextLine();
        }
        if (roomManager.getRoomID(room) != null) {
            return roomManager.getRoomID(room);
        } else {
            return getRoomChoice();
        }
    }

    /**
     * Gets the list of Events the Attendee user is signed up for
     * @return true iff the presenter has been updated with a list of Events
     */
    public boolean getUserEventList() {
        ArrayList<String> userEventList = attendeeManager.getSignedUpEvents(currentUserID);
        return presenter.printAttendeeEventList(userEventList);
    }

    /**
     * Tries to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventName) {
        EventManager thisRoom = this.roomManager.getEventRoom(eventName);
        if (thisRoom == null) {
            return false;
        }
        String event = thisRoom.getEventID(eventName);
        boolean personAddedToEvent = thisRoom.signUpForEvent(currentUserID, event);
        boolean eventAddedToPerson = attendeeManager.signUpForEvent(currentUserID, event);
        attendeeManager.addChat(currentUserID, thisRoom.getEventChat(event));
        if (personAddedToEvent && eventAddedToPerson) {
            presenter.printEventAdded();
            return true;
        }
        else if (!(personAddedToEvent)) {
            presenter.printEventFull();
            return false;
        }
        return false;
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     * @return true iff user was removed from the Event
     */
    public boolean cancelSpotFromEvent(String eventName) {
        EventManager thisRoom = this.roomManager.getEventRoom(eventName);
        if (thisRoom == null) {
            return false;
        }
        String event = thisRoom.getEventID(eventName);
        boolean personRemovedFromEvent = thisRoom.removeFromEvent(currentUserID, event);
        boolean eventRemovedFromPerson = attendeeManager.removeSpotFromEvents(currentUserID, event);
        attendeeManager.removeChat(currentUserID, thisRoom.getEventChat(event));
        if(personRemovedFromEvent && eventRemovedFromPerson) {
            presenter.printEventRemoved();
            return true;
        }
        return false;
    }
}
