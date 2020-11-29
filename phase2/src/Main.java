// Programmer: Cara McNeil
// Description: The main method
// Date Created: 01/11/2020
// Date Modified: 11/11/2020

import Presenter.Central.ConventionPlanningSystem;
import View.Central.Account;
import View.PopoutView;

public class Main {
    public static void main(String[] args) {
        ConventionPlanningSystem cps = new ConventionPlanningSystem();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                cps.run();
            }
        });
    }
}
