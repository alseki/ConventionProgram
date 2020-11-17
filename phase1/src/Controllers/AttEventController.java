package Controllers;

// Programmers: Cara McNeil, Allen Kim
// Description: All the methods that take user input in the Attendee Event Menu
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import java.util.ArrayList;

import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Events.EventManager;
import Person.PersonManager;
import Person.AttendeeManager;
import Person.SpeakerManager;
import Presenter.AttEventMenu;

import java.util.List;
import java.util.Scanner;

public class AttEventController implements SubMenu {

    private String currentUserID;
    private int currentRequest;
    private PersonManager personManager;
    private AttendeeManager attendeeManager;
    private EventManager eventManager;
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
            switch(currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1:
                    getConventionEventList();
                    break;
                case 2:
                    presenter.printAddEventPrompt();
                    String addingEventInput = input.nextLine();
                    signupForEvent(addingEventInput);
                    break;
                case 3:
                    presenter.printRemoveEventPrompt();
                    String removingEventInput = input.nextLine();
                    cancelSpotFromEvent(removingEventInput);
                    break;
                case 4:
                    getUserEventList();
                    break;
            }

            // TODO add switch statement to call the methods that correspond with currentRequest
        }
        while (currentRequest != 0);
        return true;
    }

    // TODO change, delete and/or add to the methods below

    /**
     * Get's the list of Events happening at the convention
     * @return true iff a formatted list of Events was displayed
     */
    public boolean getConventionEventList() {
        String[] allEvents = eventManager.getEventList();
        return presenter.printConventionEventList(allEvents);
    }

    /**
     * Get's the list of Events the Person.Person.Attendee user is signed up for
     * @return true iff the presenter has been updated with a list of events
     */
    public boolean getUserEventList() {
        ArrayList<String> userEventList = attendeeManager.getSignedUpEvents(currentUserID);
        return presenter.printAttendeeEventList(userEventList);
    }

    /**
     * Try to sign user up for an Event
     * @param eventName The name of the Event the current user requested to sign up for
     * @return true iff user was signed up for the Event
     */
    public boolean signupForEvent(String eventName) {

        // wanna add the event's chatId to the current user's list of chats.

        boolean isPersonAddedToEvent = eventManager.signUpForEvent(currentUserID, eventName);
        boolean isEventAddedToPerson = attendeeManager.signUpForEvent(currentUserID, eventName);

        if(isPersonAddedToEvent && isEventAddedToPerson) {
            presenter.printEventAdded();
            return true;
        }
        return false;
    }

    /**
     * Remove this user from Event
     * @param eventName The name of the Event the current user requested to cancel
     * @return true iff user was removed from the Event
     */
    public boolean cancelSpotFromEvent(String eventName) {

        // wanna remove the event's chatId to the current user's list of chats.

        boolean isPersonRemovedFromEvent = eventManager.removeFromEvent(currentUserID, eventName);
        boolean isEventRemovedFromPerson = attendeeManager.removeSpotFromEvents(currentUserID, eventName);

        if(isPersonRemovedFromEvent && isEventRemovedFromPerson) {
            presenter.printEventRemoved();
            return true;
        }
        return false;
    }

}
