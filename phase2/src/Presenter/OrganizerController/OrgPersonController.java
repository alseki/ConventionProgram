package Presenter.OrganizerController;


// Programmers:
// Description: All the methods that deal with userAccounts in OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Event.EventManager;
import Event.EventPermissions;
import Person.AttendeeManager;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.OverwritingException;

import java.util.ArrayList;

public class OrgPersonController extends SubMenu {

    private String currentUserID;
    private SpeakerManager speakerManager;
    private EmployeeManager employeeManager;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private EventPermissions eventPermissions;
    private EventManager eventManager;
    private OrgPersonMenu presenter;


    public OrgPersonController(SubMenu subMenu, String currentUserID, SpeakerManager speakerManager,
                               EmployeeManager employeeManager, AttendeeManager attendeeManager) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.speakerManager = speakerManager;
        this.employeeManager = employeeManager;
        this.attendeeManager = attendeeManager;
        this.organizerManager = (OrganizerManager)personManager;
        eventPermissions = new EventPermissions(roomManager, eventManager);
        presenter = new OrgPersonMenu(roomManager, eventManager, personManager);
    }


    // TODO remove attendees from events lists, and remove speakers from events WITHOUT deleting their accounts
    // TODO when deleting accounts: remove their ID from all chats, and remove their ID from all events & MESSAGE other users in chat that
    // TODO this person has been removed: MESSage, also attendees or Talks and panels, that this speaker has been removed or replaced
    // TODO right before a events commences yeah [4:37 PM] there's a method in eventmanager or eventpermissions or something to
    //  remove a person from an event... FOR chat, get all their chatIDS (one function) for this and delete their user ID from chat same
    // for events. get all events they'r signed up for

    // 1st helper method:
    // get attendee's eventList Ids. For each event id get that attendee list and remove attendee (with attendeeID)

    public void deleteAttendeeFromEvent(String userID) {

        ArrayList<String> eventList = personManager.getEventList(userID);
        for(String e: eventList) {
            eventPermissions.removeFromEvent(userID, e);
        }
    }

    public void deleteUserFromChatGroups(String userID) {

        ArrayList<String> userChatList = personManager.getChats(userID);
        for(String c: userChatList) {
            ArrayList<String> personList = chatManager.getPersonIds(c);
            for(String p: personList){
                sendMessageAboutChatDeletion(userID, p, c);
            }
            chatManager.removePersonIds(c, userID);
        }
    }

    public void sendMessageAboutChatDeletion(String userID, String recipientId, String chatID){
        String userName = personManager.getCurrentUsername(userID);
        String messageContent = "The user with username: " + "userName " + "is now deleted from your chat group. You cannot send" +
                "or receive messages from this person.";
        messageManager.createMessage(userID, recipientId, chatID, messageContent);
    }

    /**
     * Calls cancelAccount from Person and deletes attendee's accounts from all maps/arrays
     * @param userId
     */
    public void cancelAttendeeAccount(String userId){
        // send message to attendee that their account is about to be deleted.
        deleteAttendeeFromEvent(userId);
        deleteUserFromChatGroups(userId);
        attendeeManager.cancelAccount(userId);

    }

    public void cancelAttendeeAccountByUsername(String username){
        // send message to attendee that their account is about to be deleted.
        String userId = personManager.getCurrentUsername(username);
        deleteAttendeeFromEvent(userId);
        deleteAttendeeFromEvent(userId);
        personManager.cancelAccount(username);
    }

    public void cancelSpeakerAccount(String userId){
        personManager.cancelAccount(userId);

    }

    public void cancelSpeakerAccountByUsername(String username){
        personManager.cancelAccount(username);
    }

    public void cancelEmployeeAccount(String userId){
        employeeManager.cancelEmployeeAccount(userId);

    }

    public void cancelEmployeeAccountByUsername(String username){
        employeeManager.cancelEmployeeAccount(username);
    }


    /**
     * Creates a new Person.SpeakerController account and adds it to the system
     * @param name The name of the SpeakerController
     * @param username The username of the SpeakerController
     * @param password The password of the SpeakerController
     * @param email The email of the SpeakerController
     * @return true iff a new Person.SpeakerController object was created
     */
    public void createSpeaker(String name, String username, String password, String email) throws OverwritingException {
        // OR throws Invalid Exception
        if (!speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Person.Employee account and adds it to the system (including into a specific dictionary of employees solely)
     * @param name The name of the employee
     * @param username The username of the employee
     * @param password The password of the employee
     * @param email The email of the employee
     * @return true iff a new employee object was created
     */
    public void createEmployee(String name, String username, String password, String email) throws OverwritingException {
        // OR throws InvalidException
        if (!employeeManager.findPerson(username)) {
            employeeManager.createAccount(name, username, password, email);
        } else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Person.Attendee account and adds it to the system
     * @param name The name of the employee
     * @param username The username of the employee
     * @param password The password of the employee
     * @param email The email of the employee
     * @return true iff a new employee object was created
     */
    public void createAttendee(String name, String username, String password, String email) throws OverwritingException {
        if (!employeeManager.findPerson(username)) {
            employeeManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }



    }


//    public String createAnnouncementChat(String eventId, ArrayList<String> attendeeIds, String chatName){
//        Chat ac = new Chat(eventId, attendeeIds, chatName);
//        aChatsList.add(ac);
//        return ac.getId();



    @Override
    public SubMenuPrinter getPresenter() {
        return null;
    }
}



