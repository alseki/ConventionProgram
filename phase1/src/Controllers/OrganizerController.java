package Controllers;

// Programmers: Cara McNeil, Eytan Weinstein
// Description: Main account page for Organizer users.
// Date Created: 01/11/2020
// Date Modified: 17/11/2020

import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Events.RoomManager;
import Presenter.MainMenu;

import java.util.Scanner;

public class OrganizerController extends PersonController {
    private ChatManager chatManager = new ChatManager();
    private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private AttendeeManager attendeeManager;
    private int currentRequest;
    Scanner input = new Scanner(System.in);


    public OrganizerController(PersonManager manager, SpeakerManager speakerManager,
                               RoomManager rooms, MessageManager messages) {
        super(manager, rooms, messages);
        this.manager = (OrganizerManager) manager;
        this.speakerManager = speakerManager;
        this.attendeeManager = (AttendeeManager) manager;
    }

    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                mainMenu.printOrganizerMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        MessageController messageController = new MessageController(currentUserID, manager, messageManager,
                                chatManager);
                        messageController.menuChoice();
                        break;
                    case 3:
                        AttEventController attEventController = new AttEventController(currentUserID, attendeeManager, roomManager);
                        attEventController.menuChoice();
                        break;
                    case 4:
                        OrgEventController orgEventController = new OrgEventController(currentUserID, manager,
                                speakerManager, roomManager, messageManager, chatManager);
                        orgEventController.menuChoice();
                        break;
                }
            }
            while (currentRequest != 0);
        }
    }
    
}
