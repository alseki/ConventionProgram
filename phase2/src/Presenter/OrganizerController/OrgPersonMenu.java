package Presenter.OrganizerController;

import Event.EventManager;
import Event.EventType;
import Event.RoomManager;
import Person.PersonManager;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Presenter.PersonController.EventMenu;

public class OrgPersonMenu extends EventMenu{
    public OrgPersonMenu(RoomManager rooms, EventManager events, PersonManager persons) {
        super(rooms, events, persons)
    }


    @Override
    public String getMenuTitle() {
        return "----- Organizer Event Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        return new String[]{"Create a speaker account", "Create an attendee acount", "Create an employee account",
                "Create an organizer account", "Cancel a speaker account", "Cancel an attendee account", "Cancel an employee account",
                "Create a speaker account"};
    }

    // OPTION 1


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
     * Notifies the user that a Talk was not created because another is already scheduled
     */
    public String printCapacityError(){
        return "\nThis talk could not be added. Another talk is scheduled in this room at that time.";
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
     */
    public String printMessageContentPrompt(){
        return "\nPlease enter your announcement below";
    }

}
