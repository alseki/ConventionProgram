package Controllers;// Programmers: Cara McNeil, Sarah Kronenfeld
// Description: Main account page for Organizer users.
// Date Created: 01/11/2020
// Date Modified: 15/11/2020

import Events.EventReader;
import Message.ChatManager;
import Message.MessageManager;
import Person.OrganizerManager;
import Person.PersonManager;
import Person.SpeakerManager;
import Events.RoomManager;
import Presenter.MainMenu;

import java.util.Scanner;

public class OrganizerController extends PersonController {
    private RoomManager roomManager;
    private ChatManager chatManager = new ChatManager();
    private MessageManager messageManager = new MessageManager();
    private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private OrganizerManager manager;
    private SpeakerManager speakerManager;
    private int currentRequest;
    Scanner input = new Scanner(System.in);


    public OrganizerController(PersonManager manager, SpeakerManager speakerManager) {
        super(manager);
        this.manager = (OrganizerManager) manager;
        this.speakerManager = speakerManager;
    }

    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                mainMenu.printOrganizerMM();
                currentRequest = input.nextInt();

                switch (currentRequest) {
                    case 0:
                        saveEvents();
                        // SAVE FILES
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
                        AttEventController attEventController = new AttEventController(currentUserID, manager);
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
