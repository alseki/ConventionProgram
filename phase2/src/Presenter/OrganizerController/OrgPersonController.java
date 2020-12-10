package Presenter.OrganizerController;


// Programmers:
// Description: All the methods that deal with userAccounts in OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020


import Event.EventManager;
import Event.EventPermissions;
import Person.*;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.OverwritingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;


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
        this.organizerManager = (OrganizerManager) personManager;
        eventPermissions = new EventPermissions(roomManager, eventManager);
        presenter = new OrgPersonMenu(roomManager, eventManager, personManager);
    }


    // TODO remove attendees from events lists, and remove speakers from events WITHOUT deleting their accounts
    // TODO when deleting accounts: remove their ID from all chats, and remove their ID from all events & MESSAGE other users in chat that
    // TODO this person has been removed: MESSage, also attendees or Talks and panels, that this speaker has been removed or replaced
    // TODO right before a events commences yeah [4:37 PM] there's a method in eventmanager or eventpermissions or something to
    //  remove a person from an event... FOR chat, get all their chatIDS (one function) for this and delete their user ID from chat same
    // for events. get all events they'r signed up for

    // TODO What about deleting account ID or name from Contact's list of others ??

    // Do deletion of Organizer account - set up message to all other organizers as well.

    // 1st helper method:
    // get attendee's eventList Ids. For each event id get that attendee list and remove attendee (with attendeeID)

    public boolean removeSpeakerFromNonPanelEvent(String speakerID, String eventID) {
        // for non-panel events, this will entail cancelling the event. Organizer will have to set up new event with the new details, and attendees will have to sign up again
        // should new non-panel event will be nearly identical

        // TODO comment all sections of this function so it is legible.

        // TODO add try catch blocks

        String eventName = eventManager.getEventName(eventID);
        String chatName = eventManager.getEventChat(eventID);
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();
        LocalDateTime startTime = getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();
        if (eventHour < dayHour && eventMinute < dayMinute) {
            eventManager.removeEvent(eventID);
            String messageContent = eventName + " has been cancelled. An announcement by the event organizer will be made shortly.";
            eventMessage(eventName, chatName, messageContent);

            String organizerID = this.currentUserID;
            ArrayList<String> contacts = personManager.getContactList(organizerID);

        }
        speakerManager.removeTalk(speakerID, eventID);
        return true;
    }


    public void deleteAttendeeFromEvent(String userID) {

        ArrayList<String> eventList = personManager.getEventList(userID);
        for (String e : eventList) {
            eventPermissions.removeAttendeeFromEvent(userID, e);
        }
    }

    public boolean removeFromOtherUsersContactLists(String userID) {

        ArrayList<String> userContactList = personManager.getContactList(userID);
        for (String contactID : userContactList) {
            if (personManager.getContactList(contactID).contains(userID)) {
                personManager.getContactList(contactID).remove(userID);
            } //return true;
            return true;
        }
        return false;
    }

    public boolean deleteSpeakerFromPanel(String eventID, String speakerID) {
        ArrayList<String> panelList = eventManager.getPanelSpeakerList(eventID);
        if(panelList.contains(speakerID)) {
            eventManager.removeSpeakerFromPanel(eventID, speakerID);
            return true;
        }
        return false;
    }

    public void deleteUserFromChatGroups(String userID) {

        ArrayList<String> userChatList = personManager.getChats(userID);
        for (String c : userChatList) {
            ArrayList<String> personList = chatManager.getPersonIds(c);
            for (String p : personList) {
                sendMessageAboutChatDeletion(userID, p, c);
            }
            chatManager.removePersonIds(c, userID);
        }
    }
    // TODO implement this in all cancel methods - and make one for Employee as well - to notify all employees
    public void sendMessageAboutChatDeletion(String userID, String recipientID, String chatID) {
        String userName = personManager.getCurrentUsername(userID);
        String messageContent = "The user with username: " + "userName " + "is now deleted from your chat group. You cannot send" +
                "messages to or receive messages from this person.";
        messageManager.createMessage(userID, recipientID, chatID, messageContent);
    }

    public void sendMessageAboutCancelEmployee(String userID, String recipientID, String chatID) {
        String userName = personManager.getCurrentUsername(userID);
        String messageContent = "The employee with username: " + "userName " + "is no longer an employee. You cannot send" +
                "messages to or receive messages from this person.";
        messageManager.createMessage(userID, recipientID, chatID, messageContent);

    }

    /**
     * Calls cancelAccount from Person and deletes attendee's accounts from all maps/arrays
     *
     * @param userId
     */
    public void cancelAttendeeAccount(String userId) {
        // send message to attendee that their account is about to be deleted.
        deleteAttendeeFromEvent(userId);
        deleteUserFromChatGroups(userId);
        removeFromOtherUsersContactLists(userId);
        attendeeManager.cancelAccount(userId);

    }

    public void cancelAttendeeAccountByUsername(String username) {
        // send message to attendee that their account is about to be deleted.
        String userId = personManager.getCurrentUsername(username);
        deleteAttendeeFromEvent(userId);
        deleteAttendeeFromEvent(userId);
        removeFromOtherUsersContactLists(userId);
        personManager.cancelAccount(username);
    }

    public void cancelSpeakerAccount(String userID) {
        removeFromOtherUsersContactLists(userID);
        //Map<String, ArrayList<String>> panelList = speakerManager.getAllTalksDictionary(userID);
        ArrayList<String> panelList = speakerManager.getSpeakerInPanels(userID);

        // have organizer send message to other panelists - or organizer can do this by herself/himself
        for(String eventID: panelList){
            deleteSpeakerFromPanel(eventID, userID);
        }
        // TODO Fix the commented line!

        // ArrayList<String> nonPanelList = speakerManager.getSpeakerInNonPanels(userID);
        //for(String eventID: nonPanelList){
        //    removeSpeakerFromNonPanelEvent(eventID, userID);
        }
        // TODO Fix the commented lines!

        //speakerManager.getAllTalksDictionary(userID).clear();
        // speakerManager.getAllTalksBySpeaker(userID).clear();


        // before canceling event, get all events speaker schedule and them cancel event with event ID and speaker ID

        // write method to remove from panel above. with talk, it will just be cancelling event

    // TODO Fix the commented line!

        // personManager.cancelAccount(userID);

    // }

    public void cancelSpeakerAccountByUsername(String username) {
        String userID = personManager.getCurrentUsername(username);
        // before canceling event, get all events speaker schedule and them cancel event with event ID and speakr ID
        removeFromOtherUsersContactLists(userID);
        //Map<String, ArrayList<String>> panelList = speakerManager.getAllTalksDictionary(userID);
        ArrayList<String> panelList = speakerManager.getSpeakerInPanels(userID);

        // have organizer send message to other panelists - or organizer can do this by herself/himself
        for(String eventID: panelList){
            deleteSpeakerFromPanel(eventID, userID);
        }
        // TODO Fix the commented line!

        // ArrayList<String> nonPanelList = speakerManager.getSpeakerInNonPanels(userID);
        // for(String eventID: nonPanelList){
       //     removeSpeakerFromNonPanelEvent(eventID, userID);
        }
    // TODO Fix the commented line!

        //speakerManager.getAllTalksDictionary(userID).clear();
       // speakerManager.getAllTalksBySpeaker(userID).clear();
        // before canceling event, get all events speaker schedule and them cancel event with event ID and speaker ID

        // write method to remove from panel above. with talk, it will just be cancelling event
        //personManager.cancelAccount(userID);
        //personManager.cancelAccount(username);
    //}


    public void cancelEmployeeAccount(String userID) {
        //set up message notifying other employees and organizers
        // delete chats of employees, and if employee is still working on request, the other employees will have to look into this.
        deleteUserFromChatGroups(userID);
        removeFromOtherUsersContactLists(userID);
        // TODO Fix the commented line!

        //employeeManager.getAnnouncementChats(userID).clear();
        // employeeManager.getRequestsIDs(userID).clear();
        Map<String, Employee> map = employeeManager.getUsernameToEmployee();
        //map.remove();
        employeeManager.cancelEmployeeAccount(userID);

    }

    public void cancelEmployeeAccountByUsername(String username) {
        String userID = personManager.getCurrentUsername(username);
        deleteUserFromChatGroups(userID);
        removeFromOtherUsersContactLists(userID);

        // TODO Fix the commented line!

        //employeeManager.getAnnouncementChats(userID).clear();
        // employeeManager.getRequestsIDs(userID).clear();
        Map<String, Employee> map = employeeManager.getUsernameToEmployee();
        //map.remove();
        employeeManager.cancelEmployeeAccount(userID);
        employeeManager.cancelEmployeeAccount(username);

    }

    public boolean cancelOrganizerAccount(String userID) {
        String orgUsername = personManager.getCurrentUsername(userID);
        String messageContentToOrganizers = "Account of organizer with username: " + orgUsername + " and userID: " + userID + " has been deleted";

        ArrayList<String> fellowOrganizers = organizerManager.getOrganizerOnlyMapByID(userID);
        for (String fellowOrg : fellowOrganizers) {
            if (!fellowOrg.equals(userID) && chatManager.existChat(userID, fellowOrg)) {
                String existingChatID = chatManager.findChat(userID, fellowOrg);
                messageManager.createMessage(userID, fellowOrg, existingChatID, messageContentToOrganizers);
            } else {
                String newChatID = chatManager.createChat(userID, fellowOrg);
                personManager.addChat(userID, newChatID);
                messageManager.createMessage(userID, fellowOrg, newChatID, messageContentToOrganizers);
            }
            deleteAttendeeFromEvent(userID);
            deleteUserFromChatGroups(userID);
            removeFromOtherUsersContactLists(userID);
            organizerManager.cancelAccount(userID);
            return true;
        }
        return false;
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
        if (!attendeeManager.findPerson(username)) {
            attendeeManager.createAccount(name, username, password, email);
        }
        else {
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
    public void createOrganizer(String name, String username, String password, String email) throws OverwritingException {
        if (!organizerManager.findPerson(username)) {
            organizerManager.createAccount(name, username, password, email);
        }
        else {
            throw new OverwritingException("account");
        }

    }

    /**
     * Adds a Message with content the AnnouncementChat contained within the Event with eventName*
     * @param eventName The name of the Event
     * @param chatName The name of the Chat
     */
    private void eventMessage (String eventName, String chatName, String messageContent){
        String eventID = eventManager.getEventID(eventName);
        String chatID = eventManager.getEventChat(chatName);
        String ev = eventManager.getEventChat(eventID);
        String m = messageManager.createMessage(eventID, chatID, messageContent);
        chatManager.addMessageIds(ev, m);
    }

    /**
     * Chooses a valid start time for the new Event
     * @param time The start time as a String
     * @return The start time as a LocalDateTime object
     */
    private LocalDateTime getStartTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(time, formatter);
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



