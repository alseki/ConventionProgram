package Presenter.OrganizerController;


// Programmers:
// Description: All the methods that deal with userAccounts in OrganizerController Event Menu
// Date Created: 01/11/2020
// Date Modified: 19/11/2020

import Event.EventPermissions;
import Event.Panel;
import Person.AttendeeManager;
import Person.EmployeeManager;
import Person.OrganizerManager;
import Person.SpeakerManager;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.OverwritingException;
import Request.RequestEntity;
import Request.RequestManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

// Contributors: Eytan Weinstein, Paul-John Thomas
// Last edit: Dec 11 2020

// Architecture Level - Controller

public class OrgPersonController extends SubMenu {

    // OrgPersonController implements methods that add/remove user accounts, ...

    private String currentUserID;
    private AttendeeManager attendeeManager;
    private EmployeeManager employeeManager;
    private OrganizerManager organizerManager;
    private SpeakerManager speakerManager;
    private EventPermissions eventPermissions;
    private RequestManager requestManager;
    private OrgPersonMenu presenter;

    /**
     * Constructor for OrgPersonController objects
     *
     * @param subMenu         The submenu which implements options
     * @param currentUserID   The ID for the current user of this controller
     * @param attendeeManager The AttendeeManager which manages the Attendees at this convention
     * @param employeeManager The EmployeeManager which manages the Employees at this convention
     * @param speakerManager  The SpeakerManager which manages the Speakers at this convention
     */
    public OrgPersonController(SubMenu subMenu, String currentUserID, AttendeeManager attendeeManager, EmployeeManager
            employeeManager, SpeakerManager speakerManager) {
        super(subMenu);
        this.currentUserID = currentUserID;
        this.attendeeManager = attendeeManager;
        this.employeeManager = employeeManager;
        this.organizerManager = (OrganizerManager) personManager;
        this.speakerManager = speakerManager;
        this.eventPermissions = new EventPermissions(roomManager, eventManager);
        this.presenter = new OrgPersonMenu(roomManager, eventManager, personManager);
    }

    // Methods for creating user accounts

    /**
     * Creates a new Attendee account and adds it to the system
     *
     * @param name     The name of the Attendee
     * @param username The username of the Attendee
     * @param password The password of the Attendee
     * @param email    The email of the Attendee
     */
    public void createAttendee(String name, String username, String password, String email) throws
            OverwritingException {
        if (!attendeeManager.findPerson(username)) {
            attendeeManager.createAccount(name, username, password, email);
        } else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Employee account and adds it to the system
     *
     * @param name     The name of the Employee
     * @param username The username of the Employee
     * @param password The password of the Employee
     * @param email    The email of the Employee
     */
    public void createEmployee(String name, String username, String password, String email) throws
            OverwritingException {
        if (!employeeManager.findPerson(username)) {
            employeeManager.createAccount(name, username, password, email);
        } else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Organizer account and adds it to the system
     *
     * @param name     The name of the Organizer
     * @param username The username of the Organizer
     * @param password The password of the Organizer
     * @param email    The email of the Organizer
     */
    public void createOrganizer(String name, String username, String password, String email) throws
            OverwritingException {
        if (!organizerManager.findPerson(username)) {
            organizerManager.createAccount(name, username, password, email);
        } else {
            throw new OverwritingException("account");
        }
    }

    /**
     * Creates a new Speaker account and adds it to the system
     *
     * @param name     The name of the Speaker
     * @param username The username of the Speaker
     * @param password The password of the Speaker
     * @param email    The email of the Speaker
     */
    public void createSpeaker(String name, String username, String password, String email) throws
            OverwritingException {
        if (!speakerManager.findPerson(username)) {
            speakerManager.createAccount(name, username, password, email);
        } else {
            throw new OverwritingException("account");
        }
    }

    // Methods for deleting user accounts (and their associated helper methods)

    /**
     * Deletes an Attendee account from the system; also deletes this Attendee from all Event Attendee lists, all chats,
     * and all other users' contact lists. In addition, sends a Message to all their contacts to let them know that they
     * can no longer contact this user.
     * @param username The username of the Attendee whose account is to be deleted
     */
    public void cancelAttendeeAccount(String username) throws OverwritingException {
        if (personManager.findPerson(username)) {
            String userID = personManager.getCurrentUserID(username);
            this.deleteUserFromEvent(userID);
            this.deleteUserFromChatGroups(userID);
            this.removeFromOtherUsersContactLists(userID);
            this.deleteRequests(userID);
            attendeeManager.cancelAccount(userID);
        } else {
            throw new OverwritingException("while deleting account");
        }
    }

    /**
     * Deletes an Employee account from the system; also deletes this Employee from all chats, and all other users'
     * contact lists. In addition, sends a Message to all their contacts to let them know that they can no longer
     * contact this user.
     * @param username The username of the Attendee whose account is to be deleted
     */
    public void cancelEmployeeAccount(String username) throws OverwritingException {
        if (personManager.findPerson(username)) {
            String userID = personManager.getCurrentUserID(username);
            this.deleteUserFromChatGroups(userID);
            this.removeFromOtherUsersContactLists(userID);
            this.deleteRequests(userID);
            employeeManager.cancelEmployeeAccount(userID);
        } else {
            throw new OverwritingException("while deleting account");
        }
    }

    /**
     * This is a helper method for the account deletion methods above; removes the user from all Events for which
     * he/she is signed up.
     * @param userID The ID of the user whose account is to be deleted
     */
    public void deleteUserFromEvent(String userID) {
        ArrayList<String> eventList = personManager.getEventList(userID);
        for (String e : eventList) {
            eventPermissions.removeAttendeeFromEvent(userID, e);
        }
    }

    /**
     * This is a helper method for the account deletion methods above; deletes the user from all his/her chat groups.
     * @param userID The ID of the user whose account is to be deleted
     */
    public void deleteUserFromChatGroups(String userID) {
        ArrayList<String> userChatList = personManager.getChats(userID);
        if (!userChatList.isEmpty()) {
            for (String c : userChatList) {
                ArrayList<String> personList = chatManager.getPersonIds(c);
                for (String p : personList) {
                    this.sendMessageAboutChatDeletion(userID, p, c);
                }
                chatManager.removePersonIds(c, userID);
            }
        }
    }

    /** This is a helper method that sends a message to all contacts in a user's chat group that they can no longer
     * send/receive messages to/from that user.
     * @param userID The ID of the user
     * @param recipientID The ID of the recipient
     * @param chatID The ID of the chat
     */
    public void sendMessageAboutChatDeletion(String userID, String recipientID, String chatID) {
        String userName = personManager.getCurrentUsername(userID);
        String messageContent = "The user with username: " + userName + "is now deleted from your chat group. You " +
                "cannot send messages to or receive messages from this person.";
        messageManager.createMessage(userID, recipientID, chatID, messageContent);
    }

    /** This is a helper method that removes this user from all other users' contact lists after their account has been
     * deleted.
     * @param userID The ID of the user
     */
    public void removeFromOtherUsersContactLists(String userID) {
        ArrayList<String> userContactList = personManager.getContactList(userID);
        for (String contactID : userContactList) {
            if (personManager.getContactList(contactID).contains(userID)) {
                personManager.getContactList(contactID).remove(userID);
            }
        }
    }

    /** This is a helper method that empties requests for a user whose account is to be deleted.
     * @param userID The ID of the user
     */
    public void deleteRequests(String userID) {
        ArrayList requestIDs = requestManager.getRequestsByUser(userID);
        ArrayList requestEntities = new ArrayList<>();
        for (Object requestID : requestIDs) {
            String request = (String) requestID;
            RequestEntity entity = requestManager.getRequestEntity(request);
            requestEntities.add(entity);
        }
        for (Object request: requestEntities) {
            RequestEntity object = (RequestEntity) request;
            requestManager.removeRequest(object);
        }
    }


    // TODO See line 207

    // TODO when deleting accounts: remove their ID from all chats, and remove their ID from all events & MESSAGE other users in chat that
    // TODO right before a events commences yeah [4:37 PM] there's a method in eventmanager or eventpermissions or something to
    //  remove a person from an event... FOR chat, get all their chatIDS (one function) for this and delete their user ID from chat same
    // for events. get all events they're signed up for


    // This is a helper method for delete account

    public boolean removeSpeakerFromNonPanelEvent(String speakerID, String eventID) {
        // for non-panel events, this will entail cancelling the event. Organizer will have to set up new event with the new details, and attendees will have to sign up again
        // should a new non-panel event be nearly identical

        // TODO add try catch blocks

        // Here, checking the time of the event, so that Panel has not ALREADY started, and speaker account can not be deleted just yet
        String eventName = eventManager.getEventName(eventID);
        String chatName = eventManager.getEventChat(eventID);
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();

        //same startTime method below: the same one in OrEventController
        LocalDateTime startTime = getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();
        if (eventHour < dayHour && eventMinute < dayMinute) {
            // 1) will remove the eventManager
            eventManager.removeEvent(eventID);
            // 2) will cancel the Event
            // FIXME
            //orgEventController.cancelEvent(eventID);

            String organizerID = this.currentUserID;
        }
        // remove Talk means all events speaker is signed up to speak at: this includes allTalks and allTalksDictionary in SpeakerManager.
        // speakerManager.removeTalk(speakerID, eventID);
        return true;
    }

    public boolean removeSpeakerFromPanelEvent(String speakerID, String eventID) {
        Boolean isPanel = eventManager.getEventType(eventID).equals("PANEL");
        Panel panel = (Panel) eventManager.getEvent(eventID);
        Boolean isEmpty = panel.isPanelEmpty(eventID);
        Boolean isSpeaker = panel.isSpeakerInPanel(speakerID);

        String eventName = eventManager.getEventName(eventID);
        String chatName = eventManager.getEventChat(eventID);
        LocalDateTime now = LocalDateTime.now();
        int dayHour = now.getHour();
        int dayMinute = now.getMinute();

        //same startTime method below: the same one in OrEventController
        LocalDateTime startTime = getStartTime(eventID);
        int eventHour = startTime.getHour();
        int eventMinute = startTime.getMinute();
        if ((eventHour < dayHour) && (eventMinute < dayMinute) && isPanel && !isEmpty && isSpeaker) {
            // FIXME
            /*if (panel.numberPanelists(eventID) == 1) {
                orgEventController.removeSpeakerFromPanel(speakerID, eventID);
                orgEventController.cancelEvent(eventID);
            } else {
                orgEventController.removeSpeakerFromPanel(speakerID, eventID);
            }*/
            return true;

        }
        return false;
    }






    public void cancelSpeakerAccount(String username) throws OverwritingException {
        if(personManager.findPerson(username)) {
            String speakerID = personManager.getCurrentUserID(username);
            // this is removing speaker ID from the contact lists of other users, if speaker bothered to do that
            removeFromOtherUsersContactLists(speakerID);

            // getting the lists of nonPanel and Panels speaker to be deleted was signed up to speak at
            ArrayList<String> panels = speakerManager.getSpeakerInPanels(speakerID);
            ArrayList<String> nonPanels = speakerManager.getSpeakerInNonPanels(speakerID);

            // to make sure that panel is not empty. Remember this is speaker's list of panel events, not the Panel entity's list of speakers
            // then using this eventID to delete speaker from the Panel itself
            if (!panels.isEmpty()) {
                for (String eventId : panels) {
                    removeSpeakerFromPanelEvent(speakerID, eventId);
                }
            }

            // to make sure that non-panel list is not empty. Same as above.
            if (!nonPanels.isEmpty()) {
                for (String eventId : nonPanels) {
                    removeSpeakerFromNonPanelEvent(speakerID, eventId);
                }
            }

            // rendering "clear" all the maps and arrayLists in SpeakerManager and speaker than contains speaker's ID and person object in the
            // case of maps
            //speakerManager.getAllTalksBySpeaker(speakerID).clear();
            // speakerManager.getAllTalksBySpeaker(speakerID).clear();
            //speakerManager.getSpeakerIdAllTalks(speakerID).clear();
            speakerManager.getSpeakerInNonPanels(speakerID).clear();
            speakerManager.getSpeakerInPanels(speakerID).clear();
            speakerManager.getContactList(speakerID).clear();
            speakerManager.getChats(speakerID).clear();

            // have organizer send message to other panelists - see method below
            informOrganizersSpeakerDeletion(this.currentUserID, speakerID);
        }
        else {
            throw new OverwritingException("while delete account");
        }
    }



    public void cancelOrganizerAccount(String username) throws OverwritingException {
        if(!personManager.findPerson(username)){
            String userID = personManager.getCurrentUserID(username);

        String orgUsername = personManager.getCurrentUsername(userID);
        String messageContentToOrganizers = "Account of organizer with username: " + orgUsername + " and userID: " + userID + " has been deleted";

//        ArrayList<String> fellowOrganizers = organizerManager.getOrganizerOnlyMapByID(userID);
//        for (String fellowOrg : fellowOrganizers) {
//            if (!fellowOrg.equals(userID) && chatManager.existChat(userID, fellowOrg)) {
//                String existingChatID = chatManager.findChat(userID, fellowOrg);
//                messageManager.createMessage(userID, fellowOrg, existingChatID, messageContentToOrganizers);
//            } else {
//                String newChatID = chatManager.createChat(userID, fellowOrg);
//                personManager.addChat(userID, newChatID);
//                messageManager.createMessage(userID, fellowOrg, newChatID, messageContentToOrganizers);
//            }
//            deleteUserFromEvent(userID);
//            deleteUserFromChatGroups(userID);
//            removeFromOtherUsersContactLists(userID);
//            organizerManager.cancelAccount(userID);
//            return true;
        //}
        deleteUserFromEvent(userID);
        deleteUserFromChatGroups(userID);
        removeFromOtherUsersContactLists(userID);
        organizerManager.cancelAccount(userID);}
        else {
            throw new OverwritingException("while account");
        }


    }



    

    public boolean informOrganizersSpeakerDeletion(String currentUserID, String speakerID) {
        String speakerName = personManager.getCurrentUsername(speakerID);
        String messageContentToOrganizers = "Account of speaker with username: " + speakerName + " and userID: " + speakerID + " has been deleted";

        ArrayList<String> fellowOrganizers = organizerManager.getOrganizerOnlyMapByID(currentUserID);
        if (!fellowOrganizers.isEmpty()) {
            for (String fellowOrg : fellowOrganizers) {
                if (!fellowOrg.equals(currentUserID) && chatManager.existChat(currentUserID, fellowOrg)) {
                    String existingChatID = chatManager.findChat(currentUserID, fellowOrg);
                    messageManager.createMessage(currentUserID, fellowOrg, existingChatID, messageContentToOrganizers);
                } else {
                    String newChatID = chatManager.createChat(currentUserID, fellowOrg);
                    personManager.addChat(currentUserID, newChatID);
                    messageManager.createMessage(currentUserID, fellowOrg, newChatID, messageContentToOrganizers);
                }
            } return true;
        } return false;

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
        private LocalDateTime getStartTime (String time) throws DateTimeParseException {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(time, formatter);
        }




//    public String createAnnouncementChat(String eventId, ArrayList<String> attendeeIds, String chatName){
//        Chat ac = new Chat(eventId, attendeeIds, chatName);
//        aChatsList.add(ac);
//        return ac.getId();

        @Override
        public SubMenuPrinter getPresenter () {
            return null;
        }
    }




