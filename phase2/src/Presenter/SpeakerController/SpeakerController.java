package Presenter.SpeakerController;

// Programmer: Cara McNeil, Sarah Kronenfeld
// Description: Main account page for SpeakerController users.
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttMessageController;
import Presenter.AttendeeController.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.PersonController;
import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.SpeakerManager;
import Request.RequestManager;

public class SpeakerController extends PersonController {
    private String currentUserID;
    private SpeakerManager manager;
    String[] arrMenuOptions;

    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 3);
        this.manager = manager;
    }

    /**
     * Creates the next controller according to the user's menu choice
     */
    @Override
    public SubMenu createController(String choice) {
        if (super.loggedIn) {
            if (choice.equals(options[0])) {
                return new ContactController(this, currentUserID);
            }
            else if (choice.equals(options[1])) {
                return new MessageController(this, currentUserID);
            }
            else if (choice.equals(options[2])) {
                return new SpeEventController(this, manager, currentUserID);
            }
        }
        return null;
    }

    @Override
    public String[] getMenuOptions() {
        options  = new String[4];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 3);
        options[3] = "Event Menu";
        return options;
    }

}
