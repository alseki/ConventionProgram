package Presenter.EmployeeController;

// Description: Main account page for EmployeeController users.
/*
// RETURN controllers; no void methods

import Event.EventManager;
import Event.RoomManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.EmployeeManager;
import Presenter.Central.SubMenu;
import Presenter.PersonController.ContactController;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.PersonController;
import Request.RequestManager;
import Presenter.EmployeeController.EmpReqController;


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
                return new ContactController(this, currentUserID);
            }
            else if (choice.equals(options[2])){
                return new EmpReqController(this, currentUserID);
            }
        }
        return null;
    }

    @Override
    public String[] getMenuOptions() {
        String[] employeeOptions  = {"View the request board"};
        String[] options = new String[3];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 2);
        System.arraycopy(employeeOptions, 0, options, 2, 1);
        return options;
    }

}*/

