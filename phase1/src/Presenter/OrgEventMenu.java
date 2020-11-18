package Presenter;

// Programmers: Cara McNeil, Eytan Weinstein
// Description: Prints information pertaining to an Organizer's Event planning
// Date Created: 13/11/2020
// Date Modified: 18/11/2020

import Events.EventType;

public class OrgEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("\n----- Organizer Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To create a new room, Enter 1");
        System.out.println("To create a new event, Enter 2");
        System.out.println("To create a speaker account, Enter 3");
        System.out.println("To make an announcement to the attendees of an event, Enter 4\n");
        return true;
    }

    // OPTION 1

    /**
     * Prompts user to input the name of the Room they wish to add
     * @return true iff Room adding prompt was printed
     */
    public boolean addRoomPrompt() {
        System.out.println("\nTo create a new room, please fill in the following information:");
        return true;
    }

    /**
     * Prompts user to input the name of the Room they wish to add
     * @return true iff Room name prompt was printed
     */
    public boolean roomNamePrompt() {
        System.out.println("\nWhat is the name of the room you want to create?");
        return true;
    }

    /**
     * Prompts user to input the capacity of the Room they wish to add
     * @return true iff Room capacity prompt was printed
     */
    public boolean roomCapacityPrompt() {
        System.out.println("\nWhat is the capacity of the room you want to create?");
        return true;
    }

    // OPTION 2

    /**
     * Prompts the user to create an Event
     * @return true iff CreateEvent prompt was printed
     */
    public boolean printCreateEventPrompt(){
        System.out.println("\nTo create a new event, please fill in the following information:");
        return true;
    }

    /**
     * Prompts the user to choose the type of Event they wish to create
     * @return true iff EventType prompt was printed
     */
    public boolean printEventTypePrompt(){
        System.out.println("\nWhat kind of event do you want to create? (Enter 0 for options)");
        return true;
    }

    /**
     * Presents to the user the list of possible Event types to choose from
     * @return true iff list of EventTypes was printed
     */
    public boolean printEventTypes() {
        System.out.println();
        for(EventType t: EventType.values()) {
            System.out.println(t.toString());
        }
        System.out.println();
        return true;
    }

    /**
     * Prompts the user to choose a Room for an Event (by name)
     * @return true iff RoomName prompt was printed
     */
    public boolean printRoomNamePrompt(){
        System.out.println("\nWhich room should this event be held in? (Enter 0 for options)");
        return true;
    }

    /**
     * Presents to the user the list of possible Rooms in which this Event can be held
     * @return true iff list of Rooms was printed
     */
    public boolean printRoomNames(String[] names){
        System.out.println();
        for(String name: names) {
            System.out.println(name);
        }
        System.out.println();
        return true;
    }

    /**
     * Prompts the user to name the new Event
     * @return true iff EventName prompt was printed
     */
    public boolean printEventNamePrompt(){
        System.out.println("\nWhat is the name of this new event?");
        return true;
    }

    /**
     * Prompts the user to enter a description for the new Event
     * @return true iff Description prompt was printed
     */
    public boolean printDescriptionPrompt(){
        System.out.println("\nPlease type a brief description for this new event:");
        return true;
    }

    /**
     * Prompts the user to enter a start time for the new Event
     * @return true iff StartTime prompt was printed
     */
    public boolean printStartTimePrompt(){
        System.out.println("\nAt what time will the event start?");
        System.out.println("\n(Please enter the exact date and time of this event in 24-hour clock notation. For " +
                "example, to schedule an event at 11:30 p.m. on March 4, 2020, you would type the date, followed by" +
                "a space, followed by the 24-hour time, as follows: '2020-03-04 23:30'. Be slow and careful!");
        return true;
    }

    /**
     * Notifies the user that their start time entry was invalid
     * @return true iff notification is printed
     */
    public boolean printDateError(){
        System.out.println("\n(This is not a valid date. Try again. Be slow and careful!");
        return true;
    }

    /**
     * Prompts the user to add the username of the Speaker at this new Event
     * @return true iff SpeakerUsername prompt was printed
     */
    public boolean printSpeakerUsernamePrompt(){
        System.out.println("\nWhat is the username of the speaker in charge of the event?");
        return true;
    }

    /**
     * Notifies the user that a Talk was not created because another is already scheduled
     * @return true iff CapacityError notification was printed
     */
    public boolean printCapacityError(){
        System.out.println("\nThis talk could not be added. Another talk is scheduled in this room at that time.");
        return true;
    }

    // OPTION 3

    /**
     * Prompts the user to add a Speaker account
     * @return true iff Speaker-adding prompt was printed
     */
    public boolean printAddSpeakerPrompt(){
        System.out.println("\nTo create a new speaker account, please fill in the following information:");
        return true;

    }

    /**
     * Prompts the user to enter a name for the Speaker
     * @return true iff Speaker name prompt was printed
     */
    public boolean printAddNamePrompt(){
        System.out.println("\nWhat is the speaker's full name?");
        return true;
    }

    /**
     * Prompts the user to add a password for the Speaker
     * @return true iff Speaker password prompt was printed
     */
    public boolean printAddPasswordPrompt(){
        System.out.println("\nPlease enter a password for the speaker:");
        return true;
    }

    /**
     * Prompts the user to add a username for the Speaker
     * @return true iff Speaker username prompt was printed
     */
    public boolean printAddUsernamePrompt(){
        System.out.println("\nPlease enter a username for the speaker:");
        return true;
    }

    /**
     * Prompts the user to add an email for the Speaker
     * @return true iff Speaker email prompt was printed
     */
    public boolean printAddEmailPrompt(){
        System.out.println("\nWhat iss the speaker's e-mail address?");
        return true;
    }

    // OPTION 4

    /**
     * Prompts the user to make an announcement about an Event
     * @return true iff the announcement prompt was printed
     */
    public boolean printEventMessageIntro(){
        System.out.println("\nTo make an announcement about an event, please fill in the following information:");
        return true;
    }

    /**
     * Prompts the user to enter the content of the announcement
     * @return true iff the announcement content prompt was printed
     */
    public boolean printMessageContentPrompt(){
        System.out.println("\nPlease enter your announcement below");
        return true;
    }

}
