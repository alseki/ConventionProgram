package Controllers;

// Programmer: Cara McNeil
// Description: Controllers.Main menu for Entities.Person.Person.Attendee users.
// Date Created: 01/11/2020
// Date Modified: 13/11/2020

import Person.Attendee;
import Person.AttendeeManager;
import Person.Person;
import Person.PersonManager;

import java.util.Scanner;

public class AttendeeController extends PersonController {
    // private EventManager eManager = new EventManager(); ??
    // private Message.Message.ChatManager cManager = new Message.Message.ChatManager();
    // private Message.MessageManager mManager = new Message.MessageManager();
    Scanner input = new Scanner(System.in);
    int currentRequest;
    private String username;
    private String password;
    private String currentUserID;
    private AttendeeManager manager;

    public AttendeeController(PersonManager manager) {
        super(manager);
        this.manager = (AttendeeManager) manager;
    }

    /**
     * Allows user to login and see their account. Terminates if the user chooses to logout.
     */
    @Override
    void run() {
        // will use currentRequest to determine what methods to call
        // if any classes return false, needs to update the presenter accordingly
    }

}
