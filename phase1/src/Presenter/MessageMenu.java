package Presenter;

// Programmers: Cara McNeil, Karyn Komatsu
// Description: Prints information pertaining to a user's message information
// Date Created: 11/11/2020
// Date Modified: 12/11/2020

public class MessageMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     * @return true iff all menu options were printed
     */
    @Override
    public boolean printMenuOptions() {
        return true;
    }

    /**
     * Prompts user to enter content of the new Message.
     * @return true iff the new Message containing the entered content was created.
     */
    public boolean printMessage(){
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
     * Prompts user to print Chat.
     * @return True iff the Chat was properly printed.
     */
    public boolean printChat(){
        return true;
    }
    //TO BE FIXED (Will clarify how we'd want to print the Chat. In what format etc.
}
