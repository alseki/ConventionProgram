package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to a user's attending Event information
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

import java.util.Collection;

public class AttEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        return true;
    }

    /**
     * Prints the list of Events happening at the convention
     * @param EventList a collection of Event information to be printed
     * @return true iff all Events were printed
     */
    public boolean printConventionEventList(Collection EventList) {
        return true;
    }

    /**
     * Prints the list of Events this Attendee user has signed up for
     * @param EventList a collection of Event information to be printed
     * @return true iff all Events were printed
     */
    public boolean printAttendeeEventList(Collection EventList) {
        return true;
    }

    /**
     * Prompts the user to enter the name of the Event they want to sign up for
     * @return true iff add Event prompt was printed
     */
    public boolean printAddEventPrompt() {
        return true;
    }

    /**
     * Prints a confirmation that the user has been signed up for an Event
     * @return trrue iff Event signed up for confirmation was printed
     */
    public boolean printEventAdded() {
        return true;
    }

    /**
     * Prompts the user to enter the name of the Event they wish to remove from their Event list
     * @return true iff remove Event prompt was printed
     */
    public boolean printRemoveEventPrompt() {
        return true;
    }

    /**
     * Prints a confirmation that the user has removed an Event from their Event list
     * @return true iff Event removed confirmation was printed
     */
    public boolean printEventRemoved() {
        return true;
    }
    
}
