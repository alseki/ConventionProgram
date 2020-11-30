// Programmer: Cara McNeil
// Description: The main method
// Date Created: 01/11/2020
// Date Modified: 29/11/2020

import View.GUIView;

public class Main {
    public static void main(String[] args) {
        GUIView view = new GUIView();

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() {
                view.run();
            }
        });
    }
}
