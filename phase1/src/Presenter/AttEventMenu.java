package Presenter;

// Programmers: Cara McNeil, Allen Kim, Eytan Weinstein
// Description: Prints information pertaining to a user's attending Event information
// Date Created: 11/11/2020
// Date Modified: 17/11/2020

import java.util.ArrayList;

public class AttEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("\n----- Attendee Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To view the list of events, Enter '1'.");
        System.out.println("To sign up for an event, Enter '2'.");
        System.out.println("To cancel your spot from an event, Enter '3'.");
        System.out.println("To view the events that you are currently signed up for, Enter '4'.");

        // TODO add print statements for all the other menu options
        
    }


    public void printRoomChoicePrompt() {
        System.out.println("\n Which room's schedule do you want to see? (Press 0 for options)");
    }


    public void printList (String[] list) {
        System.out.println();
        if (list != null) {
            System.out.println("\n---");
            for (String item : list) {
                System.out.println(item);
            }
            System.out.println("---\n");
        }
        else {
            System.out.println("Sorry! No items found");
        }
    }

    /**
     * Prints the list of Events happening in a given room
     * @param eventList a collection of Event information to be printed
     * @param roomName the name of the room in which the Events are being held
     */
    public void printRoomEventList(String[] eventList, String roomName) {
        System.out.println("\nEVENTS IN ROOM " + roomName.toUpperCase() + "-");
        printList(eventList);
    }

    /**
     * Prints out an Exception thrown by the program to the user
     * @param e The exception
     */
    public void printException(Exception e) {
        System.out.println("\nSorry! That didn't work.");
        System.out.println(e.getMessage());
    }

    /**
     * Prints the list of Events this Attendee user has signed up for
     * @param EventList a collection of Event information to be printed
     */
    public void printAttendeeEventList(ArrayList<String> EventList) {
        System.out.println("-EVENTS YOU HAVE SIGNED UP FOR-");
        for(String userEvent: EventList) {
            System.out.println(userEvent);
        }
        System.out.println("---");
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
