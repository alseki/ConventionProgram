package Presenter;

// Programmers: Cara McNeil,
// Description: Prints the Main Menu options
// Date Created: 11/11/2020
// Date Modified: 13/11/2020

public class MainMenu {


    /**
     * Prints the main menu options available to every user
     * @return true iff 'Person' main menu options were printed
     */
    private boolean printPersonMM() {
        System.out.println("----- Main Menu -----");
        System.out.println("To save any account changes and return to the start page, Enter '0'.");
        System.out.println("For Contact List options (view, edit) Enter '1'.");
        System.out.println("For Message options (view current messages, send replies) Enter '2'.");
        return true;
    }

    /**
     * Prints the main menu options available to Attendees
     * @return true iff 'Attendee' main menu options were printed
     */
    public boolean printAttendeeMM() {
        printPersonMM();
        System.out.println("For Event Attending options (view conventions events, view your events, edit your events) " +
                "Enter '3'.");
        return true;
    }

    /**
     * Prints the main menu options available to Organizers
     * @return true iff 'Organizer' main menu options were printed
     */
    public boolean printOrganizerMM() {
        printAttendeeMM();
        System.out.println("For Event Organizing options (edit Event list, create Speaker accounts, add rooms) " +
                "Enter '4.");
        System.out.println("For Event Messaging options (message Event Attendees, message Event Speakers) Enter '5'.");
        return true;
    }

    /**
     * Prints the main menu options available to Speakers
     * @return true iff 'Speaker' main menu options were printed
     */
    public boolean printSpeakerMM() {
        printPersonMM();
        System.out.println("To view a list of Talks you're speaking at, Enter '3'.");
        System.out.println("For Event Messaging options (message Event Attendees) Enter '4'.");
        return true;
    }

}
