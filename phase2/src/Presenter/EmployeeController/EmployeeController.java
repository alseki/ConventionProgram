package Presenter.EmployeeController;


import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.PersonController.PersonController;
import Request.RequestManager;

// create request

// work with constraints

public class EmployeeController extends PersonController {

    public EmployeeController(SpeakerManager manager, RoomManager rooms, EventManager events,
                             MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 4);
    }

    @Override
    public SubMenu createController(String choice){
        String[] options = getMenuOptions();
        if (super.loggedIn) {
            if (choice.equals(options[0])) {
                //Send you to a MessageMenu
            }
            else if (choice.equals(options[1])) {
                // check the Request board
            }
            else if (choice.equals(options[2])) {
                // check the ConferenceConstrains board
            }
        }
        return null;
    }

}
