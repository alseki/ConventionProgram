package Presenter;

// Programmers: Cara McNeil,
// Description: Prints the Main Menu options
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

public class MainMenu {

    /**
     * Prints the main menu options available to every user
     */
    private void printPersonMM() {
        System.out.println("\n----- Main Menu -----");
        System.out.println("To save any account changes and return to the start page, Enter '0'.");
        System.out.println("For Contact List options (view, edit) Enter '1'.");
        System.out.println("For Message options (view current messages, send replies) Enter '2'.");
    }

    /**
     * Prints the main menu options available to Attendees
     */
    public void printAttendeeMM() {
        printPersonMM();
        System.out.println("For Event options (view conventions events, view your events, edit your events) " +
                "Enter '3'.");
    }

    /**
     * Prints the main menu options available to Organizers
     */
    public void printOrganizerMM() {
        printAttendeeMM();
        System.out.println("For Event Organizing options (edit Event list, create Speaker accounts, add rooms) " +
                "Enter '4.");
        System.out.println("For Event Messaging options (message Event Attendees, message Event Speakers) Enter '5'.");
        
    }

    /**
     * Prints the main menu options available to Speakers
     */
    public void printSpeakerMM() {
        printPersonMM();
        System.out.println("To view a list of Talks you're speaking at, Enter '3'.");
        System.out.println("For Event Messaging options (message Event Attendees) Enter '4'.");
    }

}
