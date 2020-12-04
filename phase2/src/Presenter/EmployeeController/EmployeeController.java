package Presenter.EmployeeController;

// Description: Main account page for EmployeeController users.

RETURN controllers; no void methods

import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.EmployeeManager;
import Presenter.Central.SubMenu;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.PersonController;
import Presenter.RequestController.RequestController;
import Request.RequestManager;

// create request
// work with constraints


public class EmployeeController extends PersonController {

    private EmployeeManager manager;

    public EmployeeController(EmployeeManager manager, RoomManager rooms, EventManager events,
                              MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 4);
        this.manager = manager;
    }

    @Override
    public SubMenu createController(String choice){
        String[] options = getMenuOptions();
        if (super.loggedIn) {
            if (choice.equals(options[0])) {
                return new MessageController(this, currentUserID);
            }
            else if (choice.equals(options[1])) {
                return new RequestController(this, currentUserID);
            }
            else if (choice.equals(options[2])) {
                return new EmpEventController(this, currentUserID);
            }
        }
        return null;
    }

}
//// Programmer: Cara McNeil, Sarah Kronenfeld
////
//// Date Created: 01/11/2020
//// Date Modified: 19/11/2020
//
//
//import Presenter.AttendeeController.AttEventController;
//        import Presenter.AttendeeController.AttMessageController;
//        import Presenter.AttendeeController.AttReqController;
//        import Presenter.Central.SubMenu;
//        import Presenter.PersonController.ContactController;
//        import Presenter.PersonController.MessageController;
//        import Presenter.PersonController.PersonController;
//        import Event.EventManager;
//        import Event.RoomManager;
//        import Message.ChatManager;
//        import Message.MessageManager;
//        import Person.SpeakerManager;
//        import Request.RequestManager;
//
//public class SpeakerController extends PersonController {
//    private SpeakerManager manager;
//
//    public SpeakerController(SpeakerManager manager, RoomManager rooms, EventManager events,
//                             MessageManager messages, ChatManager chats, RequestManager requests) {
//        super(manager, rooms, events, messages, chats, requests, 3);
//        this.manager = manager;
//    }
//
//    /**
//     * Creates the next controller according to the user's menu choice
//     */
//    @Override
//    public SubMenu createController(String choice) {
//        String[] options = getMenuOptions();
//        if (super.loggedIn) {
//            if (choice.equals(options[0])) {
//                return new ContactController(this, currentUserID);
//            }
//            else if (choice.equals(options[1])) {
//                return new MessageController(this, currentUserID);
//            }
//            else if (choice.equals(options[2])) {
//                return new SpeEventController(this, manager, currentUserID);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public String[] getMenuOptions() {
//        String[] attendeeOptions  = {"View your event information"};
//        String[] options = new String[3];
//        System.arraycopy(super.getMenuOptions(), 0, options, 0, 2);
//        System.arraycopy(attendeeOptions, 0, options, 2, 1);
//        return options;
//    }
//
//}
