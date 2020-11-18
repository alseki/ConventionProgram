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
     * Prompts user to input the name of the room they wish to add
     * @return true iff room adding prompt was printed
     */
    public boolean addRoomPrompt() {
        System.out.println("\nTo create a new room, please fill in the following information:");
        return true;
    }

    /**
     * Prompts user to input the name of the Room they wish to add
     * @return true iff room name prompt was printed
     */
    public boolean roomNamePrompt() {
        System.out.println("\nWhat is the name of the room you want to create?");
        return true;
    }

    /**
     * Prompts user to input the capacity of the Room they wish to add
     * @return true iff room capacity prompt was printed
     */
    public boolean roomCapacityPrompt() {
        System.out.println("\nWhat is the capacity of the room you want to create?");
        return true;
    }

    // OPTION 2

    /**
     * prompts to create an event
     * @return true
     */
    public boolean printCreateEventPrompt(){
        System.out.println("\nTo create a new event, please fill in the following information:");
        return true;
    }

    /**
     * Lists possible events which can be created
     * @return true
     */
    public boolean printEventTypePrompt(){
        System.out.println("\nWhat kind of event do you want to create? (Enter 0 for options)");
        return true;
    }

    public boolean printEventTypes() {
        System.out.println();
        for(EventType t: EventType.values()) {
            System.out.println(t.toString());
        }
        System.out.println();
        return true;
    }

    /**
     * prompts to name an event
     * @return true
     */
    public boolean printEventNamePrompt(){
        System.out.println("\nWhat's the event's name??");
        return true;
    }

    /**
     * prompt to choose a room for an event
     * @return true
     */
    public boolean printRoomNamePrompt(){
        System.out.println("\nWhich room is this event held in? (Enter 0 for options)");
        return true;

    }

    /**
     * Lists rooms that events can be held in
     * @return true
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
     * prompts to add the speaker username
     * @return true
     */
    public boolean printSpeakerUsernamePrompt(){
        System.out.println("\nWhat's the username of the speaker in charge of the event?");
        return true;
    }

    /**
     * prompts to enter a start time
     * @return true
     */
    public boolean printStartTimePrompt(){
        System.out.println("\nAt what time will the event start?");
        System.out.println("\n(Please enter one integer that represents the hour of the event's start-time, in " +
                "24-hour clock notation.)");
        return true;
    }

    // OPTION 3

    /**
     * prompts to add aa speaker
     * @return true
     */
    public boolean printAddSpeakPrompt(){
        System.out.println("\nTo create a new speaker account, please fill in the following information:");
        return true;

    }

    /**
     * prompts to add a name
     * @return true
     */
    public boolean printAddNamePrompt(){
        System.out.println("\nWhat's the speaker's full name?");
        return true;
    }

    /**
     * prompts to add a password
     * @return true
     */
    public boolean printAddPasswordPrompt(){
        System.out.println("\nPlease enter a password for the speaker:");
        return true;
    }

    /**
     * prompts to add username
     * @return true
     */
    public boolean printAddUsernamePrompt(){
        System.out.println("\nPlease enter a username for the speaker:");
        return true;
    }

    /**
     * prompts to add an email
     * @return true
     */
    public boolean printAddEmailPrompt(){
        System.out.println("\nWhat's the speaker's email address?");
        return true;
    }

    // OPTION 4

    /**
     * prompts an introduction for an event message
     * @return true
     */
    public boolean printEventMessageIntro(){
        System.out.println("\nTo make an announcement about an event, please fill in the following information:");
        return true;
    }

    /**
     * prompts for message content
     * @return true
     */
    public boolean printMessageContentPrompt(){
        System.out.println("\nPlease enter your announcement below");
        return true;
    }

}
