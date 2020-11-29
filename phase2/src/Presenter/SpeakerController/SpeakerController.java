package Controllers.SpeakerController;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: Main account page for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Controllers.PersonController.ContactController;
import Controllers.PersonController.MessageController;
import Controllers.PersonController.PersonController;
import Controllers.SubMenu;
import Events.EventManager;
import Events.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.SpeakerManager;
import View.Account;
import View.Account.iViewGUI;
import View.Login.iViewPane;

import java.util.Scanner;

public class SpeakerController extends PersonController {
    private Account mainMenu = new Account();
    private String currentUserID;
    private SpeakerManager manager;
    private int currentRequest;
    //private int typePerson;
    Scanner input = new Scanner(System.in);

    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats, iViewPane viewPane, iViewGUI viewGUI) {
        super(manager, rooms, events, messages, chats, 3, viewPane, viewGUI);
        this.manager = (SpeakerManager) manager;
        //this.typePerson = 3;
    }


    @Override
    public void run() {
        super.run();
        this.currentUserID = super.currentUserID;
        if (super.loggedIn) {
            do {
                // TODO replace this line with GUI equivalent: mainMenu.printSpeakerMM();
                currentRequest = SubMenu.readInteger(input);

                switch (currentRequest) {
                    case 0:
                        break;
                    case 1:
                        ContactController contactController = new ContactController(currentUserID, manager);
                        contactController.menuChoice();
                        break;
                    case 2:
                        MessageController messageController = new MessageController(currentUserID, manager,
                                messageManager, chatManager, eventManager);
                        messageController.menuChoice();
                        break;
                    case 3:
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
