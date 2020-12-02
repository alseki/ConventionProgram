package View.Central;

import Presenter.PersonController.PersonController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpeakerAccount extends PersonAccount {

    public SpeakerAccount(PersonController controller) {
        super(controller);
        menuOptions.add("SpeEventMenu");

        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if (menuSelection == "SpeEventMenu") {
            // TODO make SpeEventMenuView
            return; // FIXME
        }
    }
}
