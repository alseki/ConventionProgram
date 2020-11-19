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
     */
    @Override
    public void menuOptions() {
        presenter.printMenuOptions();
        currentRequest = SubMenu.readInteger(input);
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     */
    @Override
    public void menuChoice() {
        do {
            menuOptions();
            switch(currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1:
                    try {
                        String roomID = this.getRoomChoice();
                        presenter.printRoomEventList(roomManager.getRoom(roomID).getEventList(), roomManager.getRoomName(roomID));
                    } catch (NoDataException e) {
                        presenter.printException(e);
                    }
                    break;
                case 2:
                    presenter.printAddEventPrompt();
                    SubMenu.readInput(input);
                    String addingEventInput = SubMenu.readInput(input);
                    try {
                        signupForEvent(addingEventInput);
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }

                    break;
                case 3:
                    presenter.printRemoveEventPrompt();
                    SubMenu.readInput(input);
                    String removingEventInput = SubMenu.readInput(input);
                    try {
                         cancelSpotFromEvent(removingEventInput);
                    } catch (InvalidChoiceException e) {
                        presenter.printException(e);
                    }
                    break;
                case 4:
                    getUserEventList();
                    break;
            }
        }
        while (currentRequest != 0);

    }

    // TODO change, delete and/or add to the methods below

    /**
     * Takes user input to pick a Room for which that user wishes to view the Events held there
     * @return the ID of the Room chosen
     */
    private String getRoomChoice() throws NoDataException {
        presenter.printRoomChoicePrompt();
        SubMenu.readInput(input);
        String room = SubMenu.readInput(input);
        try {
            if (room.equals("0")) {
                presenter.printList(roomManager.getRoomNames(), "room");
                room = SubMenu.readInput(input);
            }
            if (roomManager.getRoomID(room) != null) {
                return roomManager.getRoomID(room);
            } else {
                return getRoomChoice();
            }
        }
        catch (NoDataException e) {
            throw e;
        }
    }

    /**
     * Gets the list of Events the Attendee user is signed up for
     */
    public void getUserEventList() {
        ArrayList<String> userEventList = attendeeManager.getSignedUpEvents(currentUserID);
        presenter.printAttendeeEventList(userEventList);
    }

    /**
     * Tries to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     */
    public void signupForEvent (String eventName) throws InvalidChoiceException  {
        EventManager thisRoom = roomManager.getRoom(roomManager.getEventRoom(eventName));
        if (thisRoom == null) {
            throw new InvalidChoiceException("event");
        }
        String event = thisRoom.getEventID(eventName);
        boolean personAddedToEvent = thisRoom.signUpForEvent(currentUserID, event);
        boolean eventAddedToPerson = attendeeManager.signUpForEvent(currentUserID, event);
        attendeeManager.addChat(currentUserID, thisRoom.getEventChat(event));
        if (personAddedToEvent && eventAddedToPerson) {
            presenter.printEventAdded();
        }
        else if (!(personAddedToEvent)) {
            presenter.printEventFull();
        }
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     */
    public void cancelSpotFromEvent(String eventName) throws InvalidChoiceException {
        EventManager thisRoom = roomManager.getRoom(roomManager.getEventRoom(eventName));
        if (thisRoom == null) {
            throw new InvalidChoiceException("event");
        }
        String event = thisRoom.getEventID(eventName);
        boolean personRemovedFromEvent = thisRoom.removeFromEvent(currentUserID, event);
        boolean eventRemovedFromPerson = attendeeManager.removeSpotFromEvents(currentUserID, event);
        attendeeManager.removeChat(currentUserID, thisRoom.getEventChat(event));
        if(personRemovedFromEvent && eventRemovedFromPerson) {
            presenter.printEventRemoved();
        }
    }
}
