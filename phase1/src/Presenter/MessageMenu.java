package Presenter;

import Controllers.InvalidChoiceException;
import Controllers.NoDataException;
import Message.AnnouncementChat;
import Message.ChatManager;
import Message.MessageManager;
import Person.PersonManager;

import java.util.ArrayList;

public class MessageMenu implements printSubMenu {

    protected PersonManager personManager;
    protected ChatManager chatManager;
    protected MessageManager messageManager;

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
        System.out.println('\n' + "----- Message Menu -----");
        System.out.println("Return to Main Menu -------------------------------- Enter '0'.");
        System.out.println("Check your inbox ----------------------------------- Enter '1'.");
        System.out.println("Check your sent box -------------------------------- Enter '2'.");
        System.out.println("View the chat list --------------------------------- Enter '3'.");
        System.out.println("View the messages in a chat ------------------------ Enter '4'.");
        System.out.println("Send a message ------------------------------------- Enter '5'.");
    }

    // Prompts

    /**
     * Prompts user to enter ID of the chat.
     */
    public void printChatIdPrompt(String type) {
        System.out.println("Which " + type + "Chat do you want to check? Enter the chatID.");
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


    /**
     * Prompts user to enter content of the message.
     */
    public void printContentPrompt(){
        System.out.println("Please enter the content.");
    }

    // Chat summary formatting

    /**
     * Get chat formatted as: "[ID]: [ID of the chat]\new line
     *                            [Participants]: [Username of the Participants]\newline
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     */
    public String formatChatString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getPersonIds(chatID)){
            participants.append("\n").append(personManager.getCurrentUsername(participantID));
        }
        return "ChatID: " + chatID + "\n" + "Participants: " + participants.toString() ;
    }

    /**
     * Get AnnouncementChat formatted as: "[ID]: [ID of the chat]\new line
     *                                     [Event]: [Name of event]
     * @param chatID The ID of the Chat that is to be formatted
     * @return Formatted string representation of the chat.
     * */
    public String formatAnChatString(String chatID) {
        StringBuilder participants = new StringBuilder();
        for (String participantID : chatManager.getPersonIds(chatID)){
            participants.append(participantID).append(", ");
        }
        return null;
    } //TODO: update to the specified format

    public void printFormattedChatList(ArrayList<String> chatIDs) throws NoDataException {
        String[] chatList = new String[chatIDs.size()];
        for (int i = 0; i < chatList.length; i++) {
            String chat = chatIDs.get(i);
            if (chatManager.getChatClass(chatIDs.get(i)) != AnnouncementChat.class) {
                chatList[i] = formatChatString(chat);
            } else {
                chatList[i] = formatAnChatString(chat);
            }
        }
        printList(chatList, "chat");

    }

    // Message formatting

    /**
     * Get message formatted as: "[From]: [Username of the sender\Name of the Event]\new line
     *                            [Time Sent]: [time that was sent]\newline
     *                            [Message]: [the content of the message]\newline"
     * @param messageId of the message that is to be formatted.
     * @return Formatted string representation of the message.
     */
    private String formatMessage(String messageId) {
        String time = messageManager.getDateTime(messageId);
        String message = messageManager.getContent(messageId);
        return "Time sent: " + time + "\n" + "Message: " + message;
    } //TODO - update this for events!

    public String[] formatMessages(ArrayList<String> messageIDs) throws NoDataException {
        ArrayList<String> messageInChat = new ArrayList<>();
        for (String mID : messageIDs) {
            messageInChat.add(formatMessage(mID));
        }
        String[] messages = {};
        return messageInChat.toArray(messages);
    }


    // Entire chat formatting

    /**
     * Show the messages in one chat by chatID.
     * For Phase 1 we also use this to view Announcements in AnnouncementChat.
     */
    public void printChat(String chatID) throws InvalidChoiceException {
        if (chatManager.isEmpty()) {
            throw new NoDataException("chat");
        }
        if (chatManager.isChatIDNull(chatID)) {
            throw new InvalidChoiceException("chat");
        }
        if (chatManager.getChatClass(chatID) != AnnouncementChat.class) {

            formatChatString(chatID);

            printList(formatMessages(chatManager.getMessageIds(chatID)), "message");
        } else  {
            formatAnChatString(chatID);
            printList(formatMessages(chatManager.getMessageIds(chatID)), "message");
        }
    }
}
