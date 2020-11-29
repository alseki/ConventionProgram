package Controllers;

// Programmer: Cara McNeil
// Description: The main method
// Date Created: 01/11/2020
// Date Modified: 11/11/2020

import View.Account.Account;
import View.Account.iViewGUI;
import View.Login.PopoutView;
import View.Login.iViewPane;

public class Main {
    public static void main(String[] args) {
        ConventionPlanningSystem cps = new ConventionPlanningSystem();
        iViewPane viewPane = new PopoutView();
        iViewGUI viewGUI = new Account();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                cps.run(viewPane, viewGUI);
            }
        });
    }
}
