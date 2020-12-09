package Presenter.AttendeeController;

// Programmers: Cara McNeil, Karyn Komatsu, Ran Yi, Sarah Kronenfeld
// Description: Returns information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 02/12/2020


import Event.EventManager;
import Message.*;
import Person.PersonManager;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.OverwritingException;
import Presenter.PersonController.MessageMenu;

import java.util.ArrayList;


public class AttMessageMenu extends MessageMenu {


    public AttMessageMenu(PersonManager personManager, MessageManager messageManager, ChatManager chatManager,
                          EventManager eventManager, String currentUserID) {
        super(personManager, messageManager, chatManager, eventManager, currentUserID);
    }

    @Override
    public String[] getMenuOptions() {
        String[] options = new String[10];
        System.arraycopy(super.getMenuOptions(), 0, options, 0, 6);
        options[6] = "View your list of event announcement channels";
        options[7] = "View the announcements from a particular event";
        options[8] = "Create a new chat";
        options[9] = "Create a new group chat";
        return options;
    }

    /**
     * Prompts user to enter username of the contact want to have chat with.
     */
    public String printContactUsernamePrompt(){
        return "Who do you want to chat with? Enter their username.";
    }

    /**
     * Prompts user to enter usernames of the contacts want to have chat with.
     */
    public String printContactUsernamesPrompt(){
        return "Who do you want to have a group chat with? Enter their usernames in a comma-separated list." +
                "\n(E.g. 'user1,user2,user3' without apostrophes (''). Please do not add spaces after commas)";
    }
    //Method below is left here in case we use it in Phase 2
    /**
     * Tell the User the chat is created and the ID.
     */
    public String printChatCreated(String chatID) {
        return "Chat created! The ID is: " + chatID;
    }

    /**
     * Tell the User that the chat was NOT created.
     */
    public String printChatNotCreated(Exception e){
        return "Whoops! The Chat was NOT created!\n" + printException(e);
    }

    /**
     * Tell the User that there already exists a Chat with same members as the input.
     */
    public String printChatExists(String chatID){
        return printChatNotCreated(new OverwritingException("chat")) + "\nThe ID is: " + chatID;
    }
}

class AnnouncementMessageMenu extends AttMessageMenu {

    protected AnnouncementMessageMenu(PersonManager personManager, MessageManager messageManager,
                                          ChatManager chatManager, EventManager eventManager, String currentUserID) {
        super(personManager, messageManager, chatManager, eventManager, currentUserID);
    }

    /**
     * Get AnnouncementChat formatted as: "
     *                                     [Event]: [Name of event]
     *                                     [ID]: [ID of the chat]\new line
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     * */
    @Override
    protected String formatChatString(String chatID) {
        try {
            return eventManager.getEventName(chatManager.getPersonIds(chatID).get(0)) + "\n" + chatID;
        } catch (Exception e) {
            return chatID;
        }
    }

    @Override
    protected String formatMessage(String messageId) {
        String eventName = eventManager.getEventName(messageManager.getSenderID(messageId));
        String time = messageManager.getDateTime(messageId);
        String message = messageManager.getContent(messageId);
        return "From: " + eventName + "[Event]" + "\n" +
                "Time sent:" + time + "\n" +
                "Message:" + message + "\n";
    }

    @Override
    public String getChatListTitle() {
        return "-ANNOUNCEMENT CHANNELS-";
    }

    @Override
    public String printChatIdPrompt() {
        return "Which Announcement channel do you want to check? Enter the chatID.";
    }

    public String getChatTitle(String chatID) {
        return chatID + " (" + eventManager.getEvent(chatManager.getPersonIds(chatID).get(0)) + ")" ;
    }

    // Option 6 ---------------------VIEW ANNOUNCEMENT LIST------------------

    public String getAnnouncementListTitle() {
        return "-ANNOUNCEMENTS-";
    }

    /**
     * Get Announcement formatted as:  [Title]: The name of this announcement\new line
     *                                 [Event]: The event name of this announcement
     *                                 [Participants]: [Username of the Participants]\newline
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     */
    protected String formatAnnString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getPersonIds(chatID)){
            participants.append("\n").append(personManager.getCurrentUsername(participantID));
        }
        for (String msgId : chatManager.getUnknownTypeChat(chatID).getMessageIds()) {
            if (messageManager.getReadStatus(msgId)) {
                return "[Unread]" + "\n" +
                        "Title: " + chatManager.getChatName(chatID) + "\n" +
                        "Participants: " + participants.toString();
            }
        }
        return  "Title: " + chatManager.getChatName(chatID) + "\n" +
                "Participants: " + participants.toString();
    }

    /**
     * Returns a list of formatted chat summaries
     * @param chatIDs The list of IDs the chats to print out
     * @throws InvalidChoiceException if the list is empty or the chat IDs are invalid
     */
    protected String[] getAnnouncements(ArrayList<String> chatIDs) throws InvalidChoiceException {
        String[] chatList = new String[chatIDs.size()];
        for (int i = 0; i < chatList.length; i++) {
            String chat = chatIDs.get(i);
            chatList[i] = formatAnnString(chat);
        }
        return chatList;
    }

    /**
     * Returns a list of formatted chat summaries for this user's chat
     * @throws InvalidChoiceException if the list is empty or the chat IDs are invalid
     */
    public String[] getAnnouncementList() throws InvalidChoiceException {
        ArrayList<String> unReadAnnList = new ArrayList<>();
        ArrayList<String> announcementList = new ArrayList<>();
        for (String chatId : personManager.getChats(currentUserID)) {
            if (chatManager.getUnknownTypeChat(chatId).getAnnouncementOrNot()) {
                for (String msgId : chatManager.getUnknownTypeChat(chatId).getMessageIds()) {
                    if (messageManager.getReadStatus(msgId)) {
                        unReadAnnList.add(chatId);
                    } else {
                        announcementList.add(chatId);
                    }
                }
            }
        }
        announcementList.addAll(unReadAnnList);
        return getAnnouncements(announcementList);
    }


}