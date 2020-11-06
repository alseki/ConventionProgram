package Controllers;

// Programmer: Cara McNeil
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 05/11/2020

import Controllers.OrganizerController;
import Person.*;

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
        System.out.println("To Login or create an account as an Person.Person.Attendee, Enter '1';");
        System.out.println("To Login or create an account as an Person.Person.Organizer, Enter '2';");
        System.out.println("To Login or create an account as a Person.Speaker, Enter '3';");
        return input.nextInt();
    }*/


    // This method could potentially be made into a Factory class
    /**
     * Set the user's controller based on login selection.
     */
    public void setController(int choice) {

        switch (choice) {
            case 1:
                AttendeeManager am = new AttendeeManager();
                PC = (AttendeeController) new AttendeeController(am);
                break;
            case 2:
                OrganizerManager om = new OrganizerManager();
                SpeakerManager sm = new SpeakerManager();
                PC = (OrganizerController) new OrganizerController(om, sm);
                break;
            case 3:
                SpeakerManager sman = new SpeakerManager();
                PC = (SpeakerController) new SpeakerController(sman);
                break;
        }
    }

}
