package View.Central;

import Presenter.PersonController.PersonController;

import java.awt.event.ActionEvent;

public class OrganizerAccount extends AttendeeAccount {

    public OrganizerAccount(PersonController controller) {
        super(controller);
        menuOptions.add("OrgEventMenu");

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
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
