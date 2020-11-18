package Presenter;

import java.util.ArrayList;

public class SpeEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("----- Speaker Event Menu -----");
        System.out.println("Return to Main Menu -------------------------------- Enter '0'.");
        System.out.println("View the talk you're going to talk at -------------- Enter '1'.");
        System.out.println("Send message to all Users in an Event -------------- Enter '2'.");
        System.out.println("Send message to all Attendees in an Event ---------- Enter '3'.");
        System.out.println("Send message to all Attendees in your Events ------- Enter '4'.");
        System.out.println("to be finished ------------------------ Enter '5'.");
        System.out.println("to be finished ----- Enter '6'.");
        System.out.println("Send message to one Attendee in an Event ----------- Enter '7'.");
        // TODO add print statements for all the other menu options
        
    }

    /**
     * Print String.
     */
    public void printString(String str) {
        System.out.println("str");
    }

    /**
     * Prompts user to enter eventName of the event want to send message to.
     */
    public void printEventNamePrompt() {
        System.out.println("Enter the NAME of the Event you want to send message to.");
    }

    /**
     * Prompts user to enter CONTENT of the message.
     */
    public void printContentPrompt() {
        System.out.println("Enter the CONTENT of the message.");
    }

    /**
     * Tell the User the message is sent.
     */
    public void printMessageSent() {
        System.out.println("Messages are sent!");
    }



    // Adding back into SpeEventMenu what I had from yesterday. Please integrate or discard whatever you wish.
    // I'll be working on this right now too. This is just getting a glimpse.

    /**
     * Prints the options for this menu.
     *
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Speaker Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        System.out.println(("To see the schedule of your talks, Enter '1'."));
        System.out.println("To send a message to one conference attendee, including an organizer, Enter '2'.");
        System.out.println("To send a message to all attendees of only one of your talks, Enter '3'.");
        System.out.println("To send a message to all attendees of all of your talks, Enter '4'");
        return true;
    }

    /**
     * Prompts user to print Talks.
     *
     * @return True iff the Talk was properly formatted and printed.
     */
    public boolean printFormattedTalks(ArrayList<String> eventIds) {
        System.out.println("Print Talks: \n");
        return true;

    }

    /**
     * Prints \n
     */
    public void printSkipLine() {
        System.out.println();
    }

    public boolean printRoomChoicePrompt() {
        System.out.println("\n Which room's schedule do you want to see? (Press 0 for options)");
        return true;
    }

    public boolean printSendMessageOneAttendee() {
        System.out.println("\n From which event is the attendee whom you wish to message? Enter name of event.");
        return true;
    }

    public boolean printSendMessageOneEvent() {
        System.out.println("\n Which attendee from the above event would you like to message? Enter attendee username");
        return true;
    }

    public boolean printSendMessageAllAttendees() {
        System.out.println("\n Of which event do you wish to send a message to all attendees");
        return true;
    }

    public boolean printEnterMessage() {
        System.out.println("\n Enter message to send to one attendee or all attendees");
        return true;
    }

    public boolean printSelectionAllEvents() {
        System.out.println("----- Select How To Pick All Talks -----");
        System.out.println("To enter your username to find your list of talks, Enter '1'.");
        System.out.println("To enter all event names where you are giving a talk into a list, Eneter '2'.");
        return true;
    }



}




