package View.Central;

import Presenter.PersonController.PersonController;

import java.awt.event.ActionEvent;

public class OrganizerAccount extends AttendeeAccount {

    public OrganizerAccount(PersonController controller) {
        super(controller);
        menuOptions.add("OrgEventMenu");
    }

    @Override
    public void run() {
        // Make frame
        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    public void OrgEventMenu() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if (menuSelection == "OrgEventMenu") {
            // TODO make OrgEventMenuView
            return; // FIXME
        }

    }
}
