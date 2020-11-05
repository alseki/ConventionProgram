package Controllers;

// Programmer: Cara McNeil
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 04/11/2020

import java.util.Scanner;

public class ConventionPlanningSystem {

    private PersonController PC;

    /**
    * Calls appropriate Controllers based on user input.
    */
    public void run() {
        // int accountChoice = introMessage();
        // setController(accountChoice);
        PC.run();
    }

    // This should be moved to a Presenter class
    /**
    * Greets the user and prompts them to choose a login menu
    * @return The user's menu selection (in the form of a number from 1-3)
    */
    /*public int introMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("~Welcome to ConventionSystem~" + "\n");
        System.out.println("Please select from the following options:");
        System.out.println("To Login or create an account as an Attendee, Enter '1';");
        System.out.println("To Login or create an account as an Organizer, Enter '2';");
        System.out.println("To Login or create an account as a Speaker, Enter '3';");
        return input.nextInt();
    }*/


    // This method could potentially be made into a Factory class
    /**
     * Set the user's controller based on login selection.
     */
    public void setController(int choice) {

        switch (choice) {
            case 1:
                PC = (AttendeeController) new AttendeeController();
                break;
            case 2:
                PC = (OrganizerController) new OrganizerController();
                break;
            case 3:
                PC = (SpeakerController) new SpeakerController();
                break;
        }
    }

}
