package Controllers;

// Programmer: Cara McNeil
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 11/11/2020

import Person.*;
import Presenter.CPSPresenter;

import java.util.Scanner;

public class ConventionPlanningSystem {

    private PersonController PC;
    private Scanner input = new Scanner(System.in);
    private CPSPresenter presenter = new CPSPresenter();

    /**
    * Calls appropriate Controllers based on user input.
    */
    public void run() {
        if (presenter.printIntroMessage()) {
            int accountChoice = input.nextInt();
            setController(accountChoice);
        }
        PC.run();
    }


    // This method could potentially be made into a Factory class
    /**
     * Set the user's controller based on login selection.
     */
    private void setController(int choice) {

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
