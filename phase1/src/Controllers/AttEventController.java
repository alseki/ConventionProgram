package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Attendee Event Menu
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Presenter.AttEventMenu;

import java.util.Scanner;

public class AttEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    // EventManager??
    private AttEventMenu presenter = new AttEventMenu();
    Scanner input = new Scanner(System.in);

    public AttEventController(String currentUserID, PersonManager personManager) {
        this.currentUserID = currentUserID;
        this.personManager = personManager;
    }

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff int was inputted and currentRequest was updated
     */
    @Override
    public boolean menuOptions() {
        presenter.printMenuOptions();
        // TODO update presenter class with a print statement for each option
        currentRequest = input.nextInt();
        return true;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        do {
            menuOptions();
            // TODO add switch statement to call the methods that correspond with currentRequest
        }
        while (currentRequest != 0);
        return true;
    }

    /**
     * Get's the list of Events happening at the convention
     * @return true iff a formatted list of Events was displayed
     */
    public boolean getConventionEventList() {
        // eManager.getEventList();
        // update the presenter to show the list of Events
        return false;
    }

    /**
     * Get's the list of Events the Person.Person.Attendee user is signed up for
     * @return true iff the presenter has been updated woith a list of events
     */
    public boolean getUserEventList() {
        // List Eventlist = aManager.getSignedUpForEvents(currentUserID);
        // update the presenter with the list of events signed up for (if empty, say so)
        return false;
    }

    /**
     * Try to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventName) {
        // if eManager.signup(currentUerID, eventName)
        // aManager.addEvent(currentUserID, eventName)
        // update the presenter to say the Person.Person.Attendee user's been added to the event
        return false;
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     * @return true iff user was removed from the Event
     */
    public boolean cancelEvent(String eventName) {
        // if eManager.remove(currentUserID, eventName)
        // aManager.cancelEvent(currentUserID, eventName)
        // update the presenter to say the Person.Person.Attendee user's been added to the event
        return false;
    }

}
