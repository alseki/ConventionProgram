package Presenter;

// Programmers: Cara McNeil, Allen Kim, Eytan Weinstein
// Description: Prints information pertaining to a user's attending Event information
// Date Created: 11/11/2020
// Date Modified: 17/11/2020

import java.util.Collection;
import java.util.ArrayList;

public class AttEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("\n----- Attendee Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To view the list of events, Enter '1'.");
        System.out.println("To sign up for an event, Enter '2'.");
        System.out.println("To cancel your spot from an event, Enter '3'.");
        System.out.println("To view the events that you are currently signed up for, Enter '4'.");

        // TODO add print statements for all the other menu options
        return true;
    }


    public boolean printRoomChoicePrompt() {
        System.out.println("\n Which room's schedule do you want to see? (Press 0 for options)");
        return true;
    }


    public boolean printList (String[] list) {
        System.out.println();
        if (list != null) {
            System.out.println("\n---");
            for (String item : list) {
                System.out.println(list);
            }
            System.out.println("---\n");
        }
        else {
            System.out.println("Sorry! No items found");
        }
        return true;
    }

    /**
     * Prints the list of Events happening in a given room
     * @param eventList a collection of Event information to be printed
     * @param roomName the name of the room in which the Events are being held
     * @return true iff all Events were printed
     */
    public boolean printRoomEventList(String[] eventList, String roomName) {
        System.out.println("\nEVENTS IN ROOM " + roomName.toUpperCase() + "-");
        printList(eventList);
        return true;
    }

    /**
     * Prints the list of Events this Attendee user has signed up for
     * @param EventList a collection of Event information to be printed
     * @return true iff all Events were printed
     */
    public boolean printAttendeeEventList(ArrayList<String> EventList) {
        System.out.println("-EVENTS YOU HAVE SIGNED UP FOR-");
        for(String userEvent: EventList) {
            System.out.println(userEvent);
        }
        System.out.println("---");
        return true;
    }

    /**
     * Prompts the user to enter the name of the Event they want to sign up for
     * @return true iff add Event prompt was printed
     */
    public boolean printAddEventPrompt() {
        System.out.println("Enter the exact name of the event that you would like to sign up for: ");
        return true;
    }

    /**
     * Prints a confirmation that the user has been signed up for an Event
     * @return true iff Event signed up for confirmation was printed
     */
    public boolean printEventAdded() {
        System.out.println("Event sign-up successful.");
        return true;
    }

    /**
     * Prints a notice to the user that the Event they intended to sign up is full
     * @return true iff notice was printed
     */
    public boolean printEventFull() {
        System.out.println("Event sign-up unsuccessful. This event is full.");
        return true;
    }

    /**
     * Prompts the user to enter the name of the Event they wish to remove from their Event list
     * @return true iff remove Event prompt was printed
     */
    public boolean printRemoveEventPrompt() {
        System.out.println("Enter the exact name of the event that you would like to cancel your spot from: ");
        return true;
    }

    /**
     * Prints a confirmation that the user has removed an Event from their Event list
     * @return true iff Event removed confirmation was printed
     */
    public boolean printEventRemoved() {
        System.out.println("Event spot cancellation successful.");
        return true;
    }
    
}
