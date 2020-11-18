package Presenter;

// Programmers: Cara McNeil,
// Description: Prints information pertaining to an Organizer's Event planning
// Date Created: 13/11/2020
// Date Modified: 13/11/2020

import Events.EventType;

public class OrgEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("\n----- Organizer Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To create a new room, Enter 1");
        System.out.println("To create a new event, Enter 2");
        System.out.println("To create a speaker account, Enter 3");
        System.out.println("To make an announcement to the attendees of an event, Enter 4\n");
    }

    // OPTION 1

    /**
     * Prompts user to input the name of the room they wish to add
     */
    public void addRoomPrompt() {
        System.out.println("\nTo create a new room, please fill in the following information:");
    }

    /**
     * Prompts user to input the name of the room they wish to add
     */
    public void addRoomNamePrompt() {
        System.out.println("\nWhat's the name of the room you want to create?");
    }

    //**
     //* Prompts user to input the capacity of the room they wish to add
     //* @return true iff room capacity prompt was printed
     //*/
    /*public void roomCapacityPrompt() {
        System.out.println("\nWhat is this room's maximum capacity>");
        
    }*/      // to add later, if we'd like it!



    // OPTION 2

    /**
     * prompts to create an event
     */
    public void printCreateEventPrompt(){
        System.out.println("\nTo create a new event, please fill in the following information:");
    }

    /**
     * Lists possible events which can be created
     */
    public void printEventTypePrompt(){
        System.out.println("\nWhat kind of event do you want to create? (Enter 0 for options)");
    }

    public void printEventTypes() {
        System.out.println();
        for(EventType t: EventType.values()) {
            System.out.println(t.toString());
        }
        System.out.println();
    }

    /**
     * prompts to name an event
     */
    public void printEventNamePrompt(){
        System.out.println("\nWhat's the event's name??");
    }

    /**
     * prompt to choose a room for an event
     */
    public void printRoomNamePrompt(){
        System.out.println("\nWhich room is this event held in? (Enter 0 for options)");
    }

    /**
     * Lists rooms that events can be held in
     */
    public void printRoomNames(String[] names){
        System.out.println();
        for(String name: names) {
            System.out.println(name);
        }
        System.out.println();
    }

    /**
     * prompts to add the speaker username
     */
    public void printSpeakerUsernamePrompt(){
        System.out.println("\nWhat's the username of the speaker in charge of the event?");
    }

    /**
     * prompts to enter a start time
     */
    public void printStartTimePrompt(){
        System.out.println("\nAt what time will the event start?");
        System.out.println("\n(Please enter one integer that represents the hour of the event's start-time, in " +
                "24-hour clock notation.)");
    }



    // OPTION 3

    /**
     * prompts to add aa speaker
     */
    public void printAddSpeakPrompt(){
        System.out.println("\nTo create a new speaker account, please fill in the following information:");
    }

    /**
     * prompts to add a name
     */
    public void printAddNamePrompt(){
        System.out.println("\nWhat's the speaker's full name?");
    }

    /**
     * prompts to add a password
     */
    public void printAddPasswordPrompt(){
        System.out.println("\nPlease enter a password for the speaker:");
    }

    /**
     * prompts to add username
     */
    public void printAddUsernamePrompt(){
        System.out.println("\nPlease enter a username for the speaker:");
    }

    /**
     * prompts to add an email
     */
    public void printAddEmailPrompt(){
        System.out.println("\nWhat's the speaker's email address?");
    }



    // OPTION 4

    /**
     * prompts an introduction for an event message
     */
    public void printEventMessageIntro(){
        System.out.println("\nTo make an announcement about an event, please fill in the following information:");
    }

    /**
     * prompts for message content
     */
    public void printMessageContentPrompt(){
        System.out.println("\nPlease enter your announcement below");
    }

}
