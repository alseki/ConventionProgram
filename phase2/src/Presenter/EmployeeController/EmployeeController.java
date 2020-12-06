package Presenter.EmployeeController;

 //Description: Main account page for EmployeeController users.

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

import java.util.ArrayList;

/*
// RETURN controllers; no void methods

// create request
// work with constraints
*/

public class EmployeeController extends PersonController {

    private EmployeeManager manager;

    public EmployeeController(EmployeeManager manager, RoomManager rooms, EventManager events,
                              MessageManager messages, ChatManager chats, RequestManager requests) {
        super(manager, rooms, events, messages, chats, requests, 4);
        this.manager = manager;
    }

    /**
     * Returns ID of the Employee-exclusive Chat newly created
     * @param ownerId  ID of the Employee user trying to create this Chat
     * @param guestIds ArrayList of IDs of the Employee that wants to join the Chat
     * @return ID of the newly created Chat
     */
    public String createEmpChat(String ownerId, ArrayList<String> guestIds) {
        if (manager.typePerson(manager.getCurrentUsername(ownerId)) != 4) {
            return null;
        }
        for (String guest : guestIds) {
            if (manager.typePerson(manager.getCurrentUsername(guest)) != 4) {
                return null;
            }
        }
        return this.chatManager.createChat(ownerId, guestIds);
    }

    /**
     * Returns ID of the Employee-exclusive Chat newly created
     *
     * @param ownerId  ID of the Employee user trying to create this Chat
     * @param guestId An ID of the Employee that wants to join the Chat
     * @return ID of the newly created Chat. If Chat was not created, returns null.
     */
    public String createEmpChat(String ownerId, String guestId) {
        ArrayList<String> guest = new ArrayList<>();
        guest.add(guestId);
        return createEmpChat(ownerId, guest);
    }

    /**
     * Returns ID of the Organizer-exclusive Chat newly created
     * @param ownerId ID of the Employee user trying to create this Chat
     * @param guestIds ArrayList of IDs of the Organizers that wants to join the Chat
     * @return ID of the newly created Chat
     */
    public String createEmpChatOrganizer(String ownerId, ArrayList<String> guestIds) {
        if (manager.typePerson(manager.getCurrentUsername(ownerId))!=2){return null;}
        for (String guest: guestIds){
            if (manager.typePerson(manager.getCurrentUsername(guest))!=2) {
                return null;}}
            return this.chatManager.createChat(ownerId, guestIds);
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
            else if (choice.equals(options[3])){
                return null; // FIXME
                //return new EmpEventController(this, currentUserID, manager);
            }
        }
        return null;
    }

    @Override
    public String[] getMenuOptions() {
        String[] employeeOptions  = {"View event information", "View the request board"};
        String[] options = new String[4];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 2);
        System.arraycopy(employeeOptions, 0, options, 2, 2);
        return options;
    }

}

