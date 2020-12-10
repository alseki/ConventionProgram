package Presenter.OrganizerController;

// Programmers: Cara McNeil, Eytan Weinstein
// Description: Prints information pertaining to an OrganizerController's Event planning
// Date Created: 13/11/2020
// Date Modified: 07/12/2020

import Event.EventManager;
import Event.EventType;
import Event.RoomManager;
import Person.PersonManager;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Presenter.PersonController.EventMenu;
//import com.sun.org.apache.xpath.internal.operations.String;

public class OrgEventMenu extends EventMenu {

    public OrgEventMenu(RoomManager rooms, EventManager events, PersonManager persons) {
        super(rooms, events, persons);
    }

    @Override
    public String getMenuTitle() {
        return "----- Organizer Event Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        return new String[]{"Create a new room", "Create a new event",
                "Create a speaker account", "Make an announcement to the attendees of an event"};
    }

    // OPTION 1

    /**
     * Prompts user to input the name of the Room they wish to add
     */
    public String addRoomPrompt() {
        return "\nTo create a new room, please fill in the following information:";
    }

    /**
     * Prompts user to input the name of the Room they wish to add
     */
    public String roomNamePrompt() {
        return "\nWhat is the name of the room you want to create?";
    }

    /**
     * Prompts user to input the capacity of the Room they wish to add
     */
    public String roomCapacityPrompt() {
        return "\nWhat is the capacity of the room you want to create?";
    }

    // OPTION 2

    /**
     * Prompts the user to create an Event
     */
    public String printCreateEventPrompt(){
        return "\nTo create a new event, please fill in the following information:";
    }

    /**
     * Prompts the user to choose the type of Event they wish to create
     */
    public String printEventTypePrompt(){
        return "\nWhat kind of event do you want to create? (Enter 0 for options)";
    }

    /**
     * Prompts the user to choose a type for the Event they wish to add
     * @return the type of event they have chosen (as an EventType object)
     */
    private String[] eventTypes(String type) throws InvalidChoiceException {
        EventType[] values = EventType.values();
        String[] types = new String[values.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = values[i].toString();
        }
        return types;
    }

    @Override
    public String[] getRoomList() throws NoDataException {
        try {
            return rooms.getRoomNames();
        }
        catch (NullPointerException e) {
            throw new NoDataException("room");
        }
    }

    /**
     * Prompts the user to choose a Room for an Event (by name)
     */
    public String printRoomNamePrompt(){
        return "\nWhich room is this event held in? (Enter 0 for options)";
    }

    /**
     * Prompts the user to name the new Event
     */
    public String printEventNamePrompt(){
        return "\nWhat is the name of this event?";
    }

    public String printChatNamePrompt(){
        return "\nWhat is the name of this chat?";
    }

    /**
     * Prompts the user to enter a description for the new Event
     */
    public String printDescriptionPrompt(){
        return "\nPlease type a brief description for this new event:";
    }

    /**
     * Prompts the user to enter a start time for the new Event
     */
    public String printStartTimePrompt(){
        return "\nAt what time will the event start?. \nPlease enter the exact date and time in 24-hour clock " +
                "notation. \nFor example, to schedule an event at 11:30 pm on March 4, 2020, you would type the date," +
                "followed by a space, \nfollowed by the 24-hour time, as follows: '2020-03-04 23:30'. \nBe slow and " +
                "careful!";
    }

    /**
     * Notifies the user that their start time entry was invalid
     */
    public String printDateError(){
        return "\nThis is not a valid date. Try again. Be slow and careful!";
    }

    /**
     * Prompts the user to add the username of the SpeakerController at this new Event
     */
    public String printSpeakerUsernamePrompt(){
        return "\nWhat is the username of the speaker in charge of the event?";
    }

    /**
     * Prints a notice to the user that the Event they intended to sign up is full
     */
    public String printRoomTooSmall() {
        return "That room cannot hold an event of that capacity!";
    }

    // OPTION 3

    /**
     * Prompts the user to add a SpeakerController account
     */
    public String printAddSpeakerPrompt(){
        return "\nTo create a new speaker account, please fill in the following information:";
    }

    /**
     * Prompts the user to enter a name for the SpeakerController
     */
    public String printAddNamePrompt(){
        return "\nWhat is the speaker's full name?";
    }

    /**
     * Prompts the user to add a password for the SpeakerController
     */
    public String printAddPasswordPrompt(){
        return "\nPlease enter a password for the speaker:";
    }

    /**
     * Prompts the user to add a username for the SpeakerController
     */
    public String printAddUsernamePrompt(){
        return "\nPlease enter a username for the speaker:";
    }

    /**
     * Prompts the user to add an email for the SpeakerController
     */
    public String printAddEmailPrompt(){
        return "\nWhat is the speaker's e-mail address?";
    }

    // OPTION 4

    /**
     * Prompts the user to make an announcement about an Event
     */
    public String printEventMessageIntro(){
        return "\nTo make an announcement about an event, please fill in the following information:";
    }

    /**
     * Prompts the user to enter the content of the announcement
     * @return
     */
    public java.lang.String printMessageContentPrompt(){
        return "\nPlease enter your announcement below";
    }



    // add or remove speakerfrompanel takes in eventId and speaker ID (so take in eventname and speaker username)

    //covertEventTypeToString takes in eventtype as a parameter, so take in eventName

    //Ask for eventname in changeCapacity under OrgEventController

    /**
     * Prompts user to update the capacity of an Event
     * @return
     */
    public String printUpdateCapacity(){return "\nTo update the capacity of an Event, please fill in the " +
            "following information:";}

    /**
     * Prompts user to enter the new capacity of the event represented by its ID
      * @param eventId ID of event that will have updated capacity
     * @return
     */
    public String printChangeCapacity(String eventId){return "\nPlease enter the new capacity of event: " +
            events.getEventName(eventId);}


}
