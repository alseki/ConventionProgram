package Presenter;

// Programmers: Cara McNeil, Karyn Komatsu
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 12/11/2020

import java.util.ArrayList;

public class MessageMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        System.out.println("----- Message Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        return true;
    }

    /**
     * Prompts user to enter content of the new Message.
     * @return true iff the new Message containing the entered content was created.
     */
    public boolean printCreateMessage(){
        System.out.println("Send to whom?\n Username: \n");
        System.out.println("Enter the content of message: \n");
        return true;
    }

    /**
     * Prompts user to print Message.
     * @return True iff the Message was properly formatted and printed.
     */
    public boolean printFormattedMessage(){
        System.out.println("Print Message: \n");
        return true;
    }

    /**
     * Let the presenter show the info from parameter, i.e. from MessageController.
     * @param input info from MessageController, e.g. Chat/Message information.
     */
    public void showRequiredInfo(String input){
        System.out.println(input);
    }

    /**
     * Prompts user to print Chat.
     */
    public void printChat(ArrayList<String> chats){
        for (String chat : chats){
            System.out.println(chat);
        }
    }
    //TO BE FIXED (Will clarify how we'd want to print the Chat. In what format etc.
}
