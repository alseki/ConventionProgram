package Controllers;

// Programmer: Cara McNeil
// Description: All the methods that take user input in the Organizer Event Menu
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

public class OrgEventController implements SubMenu {

    /**
     * Prompts user to choose a menu option, takes the input and calls the corresponding method
     * @return true iff choice was inputted
     */
    @Override
    public boolean menuOptions() {
        return false;
    }

    /**
     * Takes user input and calls appropriate methods, until user wants to return to Main Menu
     * @return true iff user requests to return to Main Menu
     */
    @Override
    public boolean menuChoice() {
        return false;
    }

    /**
     * Adds a room to the list of rooms in this Convention.
     * @param room The name/number of a room in the convention
     * @return true iff list was added to system.
     */
    public boolean addRoom(int room) {

        // eManager.addRoom(room);
        return false;
    }

    /**
     * Creates a new Person.Speaker account and adds it to the system.
     * @param name The name of the Speaker
     * @param username The username of the Speaker
     * @param password The password of the Speaker
     * @param email The email of the Speaker
     * @return true iff a new Person.Speaker object was created.
     */
    public boolean createSpeaker(String name, String username, String password, String email) {
        //if (sManager.findPerson(username)) {
            //sManager.createAccount(name, username, password, email);
            //return true;
        //}
        return false;
    }


    /**
     * Adds Speaker to an Event being held in a single room. At most one speaker speaks in each room at a given time
     * @param speakerUsername The username of the Speaker the current user wishes to schedule
     * @param room The name/number of a room in the convention
     * @return true iff the Speaker was added to the Event
     */
    private boolean scheduleSpeaker(String speakerUsername, String room) {

        return false;
    }


    /**
     * Creates a new Event for the convention
     * @param eventName The name of the Event the user has requested to create
     * @return true iff the Event was created
     */
    public boolean createEvent(String eventName, String speakerUsername, String room, int duration) {
        // once room manager is completed
        return false;
    }

}
