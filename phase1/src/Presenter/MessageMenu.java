package Presenter;

// Programmers: Cara McNeil, Karyn Komatsu, Ran Yi
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 18/11/2020

import java.util.ArrayList;

public class MessageMenu implements printSubMenu {

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
     * Prints out an Exception thrown by the program to the user
     * @param e The exception
     */
    public void printException(Exception e) {
        System.out.println("\nSorry! That didn't work.");
        System.out.println(e.getMessage());
    }


    public void printArrayList (ArrayList<String> aList) {
        if (aList != null) {
            System.out.println("\n---");
            for (String item : aList) {
                System.out.println(aList);
            }
            System.out.println("---\n");
        }
        else {
            System.out.println("Sorry! No items found");
        }
    }

    /**
     * Prompts user to enter ID of the chat.
     */
    public void printChatIdPrompt(){
        System.out.println("Which chat do you want to check? Enter the chatID.");
    }

    /**
     * Prompts user to enter ID of the Announcement.
     */
    public void printAnChatIdPrompt(){
        System.out.println("Which announcement chat do you want to check? Enter the AnnouncementChatID.");
    }

    /**
     * Prompts user to enter username of the contact want to have chat with.
     */
    public void printContactUsernamePrompt(){
        System.out.println("Who do you want to have chat with? Enter the Username of Him/Her/Its.");
    }

    /**
     * Prompts user to enter usernames of the contacts want to have chat with.
     */
    public void printContactUsernamesPrompt(){
        System.out.println("Who do you want to have a group chat with? Enter the Username of them use , to split.");
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
}
