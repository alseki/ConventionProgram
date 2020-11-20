package Controllers;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: Main account page for Speaker users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.AttendeeManager;
import Person.SpeakerManager;
import Presenter.MainMenu;
import Presenter.SpeEventMenu;

import java.util.Scanner;

public class SpeakerController extends PersonController {
    private MainMenu mainMenu = new MainMenu();
    private String currentUserID;
    private SpeakerManager manager;
    private int currentRequest;
    Scanner input = new Scanner(System.in);

    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats) {
        super(manager, rooms, events, messages, chats);
    }


    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                mainMenu.printSpeakerMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        SpeEventController speEventController = new SpeEventController(currentUserID, manager,
                                messageManager, chatManager, eventManager, roomManager);
                        speEventController.menuChoice();
                        break;
                }
            }
            while (currentRequest != 0);
        }
    }

}
