package Presenter;

// Programmers: Cara McNeil
// Description: Presenter for the ConventionPlanningSystem, prints info to the UI
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

public class CPSMenu {

    /**
     * Greets the user and prompts them to choose a login menu
     * @return true iff the user's menu selection has been printed
     */
    public boolean printIntroMessage() {
        System.out.println("~Welcome to ConventionSystem~" + "\n");
        System.out.println("Please select from the following options:");
        System.out.println("To Login or create an account as an Attendee, Enter '1';");
        System.out.println("To Login or create an account as an Organizer, Enter '2';");
        System.out.println("To Login or create an account as a Speaker, Enter '3';");
        System.out.println("To Quit the program and save any changes made, Enter '0'.");
        return true;
    }
}
