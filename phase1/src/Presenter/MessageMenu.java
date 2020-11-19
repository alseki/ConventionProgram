package Presenter;

// Programmers: Cara McNeil, Karyn Komatsu, Ran Yi, Sarah Kronenfeld
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 18/11/2020

import Controllers.InvalidChoiceException;
import Controllers.NoDataException;
import Message.*;
import Person.PersonManager;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageMenu implements printSubMenu {

    private PersonManager personManager;
    private ChatManager chatManager;
    private MessageManager messageManager;

    public MessageMenu(PersonManager personManager, MessageManager messageManager, ChatManager chatManager) {
        this.personManager = personManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
    }

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("----- Message Menu -----");
        System.out.println("Return to Main Menu -------------------------------- Enter '0'.");
        System.out.println("Check your inbox ----------------------------------- Enter '1'.");
        System.out.println("Check your sent box -------------------------------- Enter '2'.");
        System.out.println("View the chat list --------------------------------- Enter '3'.");
        System.out.println("View the announcement chat list -------------------- Enter '4'.");
        System.out.println("View the messages in a chat ------------------------ Enter '5'.");
        System.out.println("View the announcements in an announcement chat ----- Enter '6'.");
        System.out.println("Create a chat -------------------------------------- Enter '7'.");
        System.out.println("Create a group chat -------------------------------- Enter '8'.");
        System.out.println("Send a message ------------------------------------- Enter '9'.");
    }

    /**
     * Prompts user to enter ID of the chat.
     */
    public void printChatIdPrompt(String type){
        System.out.println("Which " + type + "Chat do you want to check? Enter the chatID.");
    }

    /**
     * Prompts user to enter username of the contact want to have chat with.
     */
    public void printContactUsernamePrompt(){
        System.out.println("Who do you want to chat with? Enter their username.");
    }

    /**
     * Prompts user to enter usernames of the contacts want to have chat with.
     */
    public void printContactUsernamesPrompt(){
        System.out.println("Who do you want to have a group chat with? Enter their usernames in a comma-separated list.");
    }

    /**
     * Tell the User the chat is created and the ID.
     */
    public void printChatCreated(String chatID){
        System.out.println("Chat created! The ID is: " + chatID);
    }

    /**
     * Tell the User any action is succeed.
     */
    public void printJobDone(){
        System.out.println("Done!");
    }

    /**
     * Prompts user to enter chatID of the chat want to send message in.
     */
    public void printChatIdMessagePrompt(){
        System.out.println("Which chat do you want to send message to? Enter the chatID.");
    }

    public void printFormattedChatList(ArrayList<String> chatIDs) throws NoDataException {
        String[] chatList = new String[chatIDs.size()];
        for (int i = 0; i < chatList.length; i++) {
            String chat = chatIDs.get(i);
            if (chatManager.getChat(chat).getClass().equals(AnnouncementChat.class)) {
                chatList[i] =formatAnChatString(chat);
            }
            else {
                chatList[i] = formatChatString(chat);
            }
        }
        printList(chatList, "chat");

    }

    public String[] formatMessages(ArrayList<String> messageIDs) throws NoDataException {
        ArrayList<String> messageInChat = new ArrayList<>();
        for (String mID : messageIDs) {
            messageInChat.add(formatMessage(mID));
        }
        String[] messages = {};
        return messageInChat.toArray(messages);
    }

    /**
     * Get message formatted as: "[From]: [Username of the sender]\new line
     *                            [Time Sent]: [time that was sent]\newline
     *                            [Message]: [the content of the message]\newline"
     * @param messageId of the message that is to be formatted.
     * @return Formatted string representation of the message.
     */
    private String formatMessage(String messageId) {
        String sender = messageManager.getSenderID(messageId);
        String recipient = messageManager.getRecipientId(messageId);
        String time = messageManager.getDateTime(messageId);
        String message = messageManager.getContent(messageId);
        return "From: " + sender + "\n" + "To: " + recipient + "\n" + "Time sent: " + time + "\n"
                + "Message: " + message + "\n";
    }//TODO: update to match formatting
    /**
     * Get chat formatted as: "[ID]: [ID of the chat]\new line
     *                            [Participants]: [Username of the Participants]\newline
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     */
    public String formatChatString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getChat(chatID).getPersonIds()){
            participants.append(participantID).append("\n");
        }
        return "ChatID: " + chatID + "\n" + "Participants: " + "\n" + participants + "\n";
    } //TODO: update to the specified format


    /**
     * Get AnnouncementChat formatted as: "[ID]: [ID of the chat]\new line
     *                                     [Event]: [Name of event]
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     * */
    public String formatAnChatString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getChat(chatID).getPersonIds()){
            participants.append(participantID).append("\n");
        }
        return null;
    } //TODO: update to the specified format


    /**
     * Prompts user to enter content of the message.
     */
    public void printContentPrompt(){
        System.out.println("Please enter the content.");
    }

    /**
     * Let the presenter show the info from parameter, i.e. from MessageController.
     * @param input info from MessageController, e.g. Chat/Message information.
     */
    public void showRequiredInfo(String input){
        System.out.println(input);
    }

    /**
     * Show the messages in one chat by chatID.
     * For Phase 1 we also use this to view Announcements in AnnouncementChat.
     */
    public void printChat(String chatID) throws InvalidChoiceException {
        if (chatManager.isEmpty()) {
            throw new NoDataException("chat");
        }
        if (chatManager.getChat(chatID) == null) {
            throw new InvalidChoiceException("chat");
        }

        if (chatManager.getChat(chatID).getClass().equals(AnnouncementChat.class)) {
            formatAnChatString(chatID);
        }
        else {
            formatChatString(chatID);
        }

        printList(formatMessages(chatManager.getChat(chatID).getMessageIds()), "message");
    }
}
