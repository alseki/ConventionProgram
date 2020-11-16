package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's attending Event information
// Date Created: 11/11/2020
// Date Modified: 14/11/2020

import java.util.Collection;
import java.util.ArrayList;

public class AttEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Attendee Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To view the list of events, Enter '1'.");
        System.out.println("To sign up for an event, Enter '2'.");
        System.out.println("To cancel your spot from an event, Enter '3'.");
        System.out.println("To view the events that you are currently signed up for, Enter '4'.");

        // TODO add print statements for all the other menu options
        return true;
    }

    /**
     * Prints the list of Events happening at the convention
     * @param EventList a collection of Event information to be printed
     * @return true iff all Events were printed
     */
    public boolean printConventionEventList(String[] EventList) {
        System.out.println("-ALL EVENTS-");
        for (String eventInfo: EventList) {
            System.out.println(eventInfo);
        }
        System.out.println("---");
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
