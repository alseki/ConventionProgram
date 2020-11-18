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

}
