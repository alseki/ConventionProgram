package Controllers;

// Programmer: Cara McNeil
// Description: The central Controller of the Convention System. Calls all other Controllers.
// Date Created: 01/11/2020
// Date Modified: 11/11/2020

import Person.*;
import Presenter.CPSMenu;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.Scanner;

public class ConventionPlanningSystem {

    private int accountChoice;
    private Scanner input = new Scanner(System.in);
    private CPSMenu presenter = new CPSMenu();

    /**
    * Calls appropriate Controllers based on user input.
    */
    public void run() {
        do {
            if (presenter.printIntroMessage()) {
                accountChoice = input.nextInt();
            }
            setController(accountChoice);
        }
        while (accountChoice != 0);
    }


    /**
     * Set the user's controller based on login selection.
     */
    private void setController(int choice) {

        switch (choice) {
            case 0:
                // SAVE FILES
                break;
            case 1:
                AttendeeManager am = new AttendeeManager();
                AttendeeController AC =  new AttendeeController(am);
                AC.run();
                break;
            case 2:
                OrganizerManager om = new OrganizerManager();
                SpeakerManager sm = new SpeakerManager();
                OrganizerController OC = new OrganizerController(om, sm);
                OC.run();
                break;
            case 3:
                SpeakerManager sman = new SpeakerManager();
                SpeakerController SC = new SpeakerController(sman);
                SC.run();
                break;
        }
    }

}
