package Presenter;

import java.util.ArrayList;

public class SpeEventMenu implements printSubMenu {

    /**
     * Prints the options for this menu.
     */
    @Override
    public void printMenuOptions() {
        System.out.println("----- Speaker Event Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        // TODO add print statements for all the other menu options
        
    }

    /**
     * Prompts user to print Talks.
     */
    public void printFormattedTalks(ArrayList<String> eventIds) {
        System.out.println("Print Talks: \n");
        }

}
