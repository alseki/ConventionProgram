package Presenter.PersonController;

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.Exceptions.NoDataException;
import Event.EventManager;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;

import java.util.ArrayList;

// Programmers: Cara McNeil, Karyn Komatsu, Ran Yi, Sarah Kronenfeld
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 02/12/2020

public class MessageMenu implements SubMenuPrinter {

    protected PersonManager personManager;
    protected ChatManager chatManager;
    protected MessageManager messageManager;
    protected EventManager eventManager;
    protected String currentUserID;

    public MessageMenu(PersonManager personManager, MessageManager messageManager, ChatManager chatManager,
                       EventManager eventManager, String currentUserID) {
        this.eventManager = eventManager;
        this.personManager = personManager;
        this.messageManager = messageManager;
        this.chatManager = chatManager;
        this.currentUserID = currentUserID;
    }

    @Override
    public String getMenuTitle() {
        return "----- Message Menu -----";
    }

    @Override
    public String[] getMenuOptions() {
        return new String[]{"Check your inbox", "Check your sent box", "View the chat list," +
                "View the messages in a chat", "Send a message"};
    }




    // Option 1

    public String getInboxTitle() {
        return "-INBOX-";
    }

    /**
     * Show all the messages this user received in presenter, **sorted by datetime.
     */
    public String[] getInBox() throws NoDataException{
        try {
            ArrayList<String> receivedMessages = new ArrayList<>();
            for (String message: messageManager.getMessageIDs()){
                if (messageManager.getRecipientId(message).equals(currentUserID)){
                    receivedMessages.add(message);
                }
            }

            return formatMessages(receivedMessages);
        } catch (NullPointerException e) {
            throw new NoDataException("message");
        }
    }


    // Option 2

    public String getOutboxTitle() {
        return "-OUTBOX-";
    }

    /**
     * Show all the messages this user sent in presenter, **sorted by datetime.
     */
    public String[] getOutBox() throws NoDataException{
        try {
            ArrayList<String> sentMessages = new ArrayList<>();
            for (String message: messageManager.getMessageIDs()){
                if (messageManager.getSenderID(message).equals(currentUserID)){
                    sentMessages.add(message);
                }
            }

            return formatMessages(sentMessages);
        } catch (NullPointerException e) {
            throw new NoDataException("message");
        }
    }


    // Option 3

    public String getChatListTitle() {
        return "-CHATS-";
    }

    /**
     * Get chat formatted as: "[ID]: [ID of the chat]\new line
     *                            [Participants]: [Username of the Participants]\newline
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     */
    protected String formatChatString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getPersonIds(chatID)){
            participants.append("\n").append(personManager.getCurrentUsername(participantID));
        }
        return "ChatID: " + chatID + "\n" + "Participants: " + participants.toString() ;
    }

    /**
     * Returns a list of formatted chat summaries
     * @param chatIDs The list of IDs the chats to print out
     * @throws InvalidChoiceException if the list is empty or the chat IDs are invalid
     */
    protected String[] getChats(ArrayList<String> chatIDs) throws InvalidChoiceException {
        String[] chatList = new String[chatIDs.size()];
        for (int i = 0; i < chatList.length; i++) {
            String chat = chatIDs.get(i);
            chatList[i] = formatChatString(chat);
        }
        return chatList;
    }

    /**
     * Returns a list of formatted chat summaries for this user's chat
     * @throws InvalidChoiceException if the list is empty or the chat IDs are invalid
     */
    public String[] getChatList() throws InvalidChoiceException {
        return getChats(personManager.getChats(currentUserID));
    }


    // Option 4

    public String getChatTitle(String chatID) {
        StringBuilder participants = new StringBuilder();
        ArrayList<String> personIDs = chatManager.getPersonIds(chatID);
        participants.append(personManager.getCurrentUsername(personIDs.get(0)));
        if (personIDs.size() > 1) {
            for (int i = 1; i <= personIDs.size(); i++) {
                participants.append(", ").append(personManager.getCurrentUsername(personIDs.get(i)));
            }
        }
        return chatID + " (" + participants.toString() + ")" ;
    }

    /**
     * Prompts user to enter ID of the chat.
     */
    public String printChatIdPrompt() {
        return "Which Chat do you want to check? Enter the chatID.";
    }

    /**
     * Show the messages in one chat by chatID.
     * For Phase 1 we also use this to view Announcements in AnnouncementChat.
     */
    public String[] getChat(String chatID) throws InvalidChoiceException {
        if (chatManager.isChatIDNull(chatID)) {
            throw new InvalidChoiceException("chat");
        }
        return formatMessages(chatManager.getMessageIds(chatID));
    }


    // Option 5

    /**
     * Prompts user to enter chatID of the chat want to send message in.
     */
    public String printChatIdMessagePrompt(){
        return "Which chat do you want to send message to? Enter the chatID.";
    }


    /**
     * Prompts user to enter content of the message.
     */
    public void printContentPrompt(){
        System.out.println("Please enter the content of your message.");
    }

    protected String messageSent() {
        return "Message sent!";
    }

    // ----------------------------- Helpers ---------------------------------
    // Message formatting

    /**
     * Get message formatted as: "[From]: [Username of the sender\Name of the Event]\new line
     *                            [To]: [Username of the receiver/null]
     *                            [Time Sent]: [time that was sent]\newline
     *                            [Message]: [the content of the message]\newline"
     * @param messageId of the message that is to be formatted.
     * @return Formatted string representation of the message.
     */
    protected String formatMessage(String messageId) {
        String sender = personManager.getCurrentUsername(messageManager.getSenderID(messageId));
        String receiver = personManager.getCurrentUsername(messageManager.getRecipientId(messageId));
        String time = messageManager.getDateTime(messageId);
        String message = messageManager.getContent(messageId);
        return "From: " + sender + "[Username]" + "\n" +
                "To: " + receiver + "\n" +
                "Time sent:" + time + "\n" +
                "Message:" + message + "\n";
    }

    protected String[] formatMessages(ArrayList<String> messageIDs) throws NoDataException {
        if (messageIDs == null || messageIDs.size() == 0) {
            throw new NoDataException("message");
        }
        ArrayList<String> messageInChat = new ArrayList<>();
        for (String mID : messageIDs) {
            messageInChat.add(formatMessage(mID));
        }
        String[] messages = {};
        return messageInChat.toArray(messages);
    }

    /**
     * Prints that a message was successfully sent
     */
    public String printMessageSent() {
        return "Message has been successfully sent.";
    }

    /**
     * Prints that the user has no messages received.
     */
    public String printNoMessageReceived() {
        return "Inbox is EMPTY!";
    }
}
