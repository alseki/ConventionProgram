package View.Central;

import Presenter.PersonController.PersonController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class AttendeeAccount extends PersonAccount {


    public AttendeeAccount(PersonController controller) {
        // Everything needed for the frame should be instantiated here
        super(controller);
        menuOptions.add("AttEventMenu");
        menuOptions.add("AttMessageMenu");
        menuOptions.add("EventMenu");
        menuOptions.add("RequestMenu");

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if (menuSelection == "AttEventMenu") {
            // TODO make AttEventMenuView
            return; // FIXME
        }

        if (menuSelection == "AttMessageMenu") {
            // TODO make AttMessageMenuView
            return; // FIXME
        }

        if (menuSelection == "EventMenu") {
            // TODO make EventMenuView
            return; // FIXME
        }

        if (menuSelection == "RequestMenu") {
            // TODO make RequestMenuView
            return; // FIXME
        }

    }
}
