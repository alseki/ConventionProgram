package Presenter.AttendeeController;

// Programmers: Cara McNeil, Allen Kim, Eytan Weinstein, Sarah Kronenfeld
// Description: Prints information pertaining to a user's attending Event information
// Date Created: 11/11/2020
// Date Modified: 19/11/2020


import Event.EventManager;
import Event.RoomManager;
import Person.AttendeeManager;
import Presenter.PersonController.EventMenu;

public class AttEventMenu extends EventMenu {
    AttendeeManager attendeeManager;
    String currentUserID;

    public AttEventMenu(String currentUserID, RoomManager rooms, EventManager events, AttendeeManager persons) {
        super(rooms, events, persons);
        attendeeManager = persons;
        this.currentUserID = currentUserID;
    }

    @Override
    public String getMenuTitle() {
        return "----- Attendee Event Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        String[] options = {"Return to Main Menu", "View conference event list", "Sign up for an event",
                "Cancel your spot in an event", "View events you've signed up for"};
        return options;
    }

    // Option 1

    public void printRoomChoicePrompt() {
        System.out.println("\n Which room's schedule do you want to see? \n (Press 0 for options, or press 1 to see " +
                "all events in the conference.)");
    }

    /**
     * Prints the list of Events happening in a given room
     * @param eventList a collection of Event information to be printed
     * @param roomName the name of the room in which the Events are being held
     */
    public void printRoomEventList(String[] eventList, String roomName) {
        printEventList(" IN ROOM " + roomName.toUpperCase(), eventList);
    }

    /**
     * Prints the list of Events this AttendeeController user has signed up for
     */
    public void printAttendeeEventList() {
        String[] evList = {};
        evList = attendeeManager.getSignedUpEvents(currentUserID).toArray(evList);
        printEventList(" you have signed up for", evList);
    }

    /**
     * Prompts the user to enter the name of the Event they want to sign up for
     */
    public void printAddEventPrompt() {
        System.out.println("Enter the exact name of the event that you would like to sign up for: ");
    }

    /**
     * Prints a confirmation that the user has been signed up for an Event
     */
    public void printEventAdded() {
        System.out.println("Event sign-up successful.");
    }

    /**
     * Prints a notice to the user that the Event they intended to sign up is full
     */
    public void printEventFull() {
        System.out.println("Event sign-up unsuccessful. This event is full.");
    }

    /**
     * Prompts the user to enter the name of the Event they wish to remove from their Event list
     */
    public void printRemoveEventPrompt() {
        System.out.println("Enter the exact name of the event that you would like to cancel your spot from: ");
    }

    /**
     * Prints a confirmation that the user has removed an Event from their Event list
     */
    public void printEventRemoved() {
        System.out.println("Event spot cancellation successful.");
    }
    
}
