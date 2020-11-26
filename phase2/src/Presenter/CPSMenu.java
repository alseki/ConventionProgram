package Presenter;

import javax.swing.JOptionPane;

// Programmers: Cara McNeil
// Description: Presenter for the ConventionPlanningSystem, prints info to the UI
// Date Created: 11/11/2020
// Date Modified: 11/11/2020

public class CPSMenu {

    /**
     * Greets the user and prompts them to choose a login menu
     */
    public String printIntroMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to convention system",
                "CONVENTION SYSTEM LOGIN", JOptionPane.PLAIN_MESSAGE);

        String message = "To Login or create an account as an Attendee, Enter '1';" + "\nTo Login or create an account " +
                "as an Organizer, Enter '2';" + "\nTo Login or create an account as a Speaker, Enter '3';" +
                "\nTo Quit the program and save any changes made, Enter '0'.";

        return JOptionPane.showInputDialog(null, message, "User Account Selection",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
