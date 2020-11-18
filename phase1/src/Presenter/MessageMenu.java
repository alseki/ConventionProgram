package Presenter;

// Programmers: Cara McNeil, Karyn Komatsu
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 12/11/2020

import java.util.ArrayList;

public class MessageMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("----- Message Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        
    }

    /**
     * Prompts user to enter content of the new Message.
     */
    public void printCreateMessage(){
        System.out.println("Send to whom?\n Username: \n");
        System.out.println("Enter the content of message: \n");
        
    }

    /**
     * Prompts user to print Message.
     */
    public void printFormattedMessage(){
        System.out.println("Print Message: \n");
        
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
