package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to an Organizer's Event planning
// Date Created: 13/11/2020
// Date Modified: 13/11/2020

public class OrgEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Organizer Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To add a room, Enter 1");
        System.out.println("To create a speaker account, Enter 2");
        System.out.println("To message an event, Enter 3");
        System.out.println("To create an event, Enter 4");


        // TODO add print statements for all the other menu options
        return true;
    }

    /**
     * Prompts user to input the room they wish to add
     * @return true iff room adding prompt was printed
     */
    public boolean printRoomPrompt() {
        System.out.println("To create a new room please enter the name of the room: ");
        return true;
    }

    /**
     * prompts to add aa speaker
     * @return true
     */
    public boolean printAddSpeakPrompt(){
        System.out.println("To create a new speaker do the following: ");
        return true;

    }

    /**
     * prompts to add a name
     * @return true
     */
    public boolean printAddNamePrompt(){
        System.out.println("please write the full name of the speaker");
        return true;
    }

    /**
     * prompts to add a password
     * @return true
     */
    public boolean printAddPasswordPrompt(){
        System.out.println("please enter your password");
        return true;
    }

    /**
     * prompts to add username
     * @return true
     */
    public boolean printAddUsernamePrompt(){
        System.out.println("please enter your username");
        return true;
    }

    /**
     * prompts to add an email
     * @return true
     */
    public boolean printAddEmailPrompt(){
        System.out.println("please enter your email");
        return true;
    }

    /**
     * prompts to create an event
     * @return true
     */
    public boolean printCreateEventPrompt(){
        System.out.println("follorw the instructiosn to create an event");
        return true;
    }

    /**
     * prompts to name an event
     * @return true
     */
    public boolean printEventNamePrompt(){
        System.out.println("Enter the event name");
        return true;
    }

    /**
     * prompt to name a roome
     * @return true
     */
    public boolean printRoomNamePrompt(){
        System.out.println("Enter the room name");
        return true;

    }

    /**
     * prompts to add the speaker username
     * @return true
     */
    public boolean printSpeakerUsernamePrompt(){
        System.out.println("Enter the speaker username");
        return true;
    }

    /**
     * prompts to enter a start time
     * @return true
     */
    public boolean printStartTimePrompt(){
        System.out.println("Enter the start time");
        return true;
    }

    /**
     * prompts an introduction for an event message
     * @return true
     */
    public boolean printEventMessageIntro(){
        System.out.println("Follow the instructions to send an event message");
        return true;
    }

    /**
     * prompts for message content
     * @return true
     */
    public boolean printMessageContentPrompt(){
        System.out.println("Enter the message content");
        return true;
    }






}
