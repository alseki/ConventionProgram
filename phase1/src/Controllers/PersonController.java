package Controllers;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: abstract main account page for other controllers to inherit from
// Date Created: 01/11/2020
// Date Modified: 15/11/2020

import Events.RoomManager;
import Person.PersonManager;

import java.util.Scanner;

abstract public class PersonController {
    Scanner input = new Scanner(System.in);
    public String currentUserID;
    private PersonManager manager;
    private RoomManager rooms;
    private LoginController loginController;
    private FileGateway<RoomManager> roomSaver = new FileGateway<RoomManager>();
    private static final String roomFileName = "events.ser";
    public boolean loggedIn =  false;


    public PersonController(PersonManager manager) {
        this.manager = manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    public void run() {
        loginController = new LoginController(manager);
        if (loginController.menuChoice()) {
            currentUserID = manager.getCurrentUserID(loginController.username);
            rooms = loadEvents();
            loggedIn = true;
        }
    }

    /**
     * Saves the current Room configuration
     */
    protected void saveEvents() {
        roomSaver.writeFile(rooms, roomFileName);
    }

    /**
     * Loads the Room configuration from a file
     */
    protected RoomManager loadEvents() {
        return roomSaver.readFile(roomFileName);
    }

}
