package View.Central;

import Presenter.PersonController.PersonController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SpeakerAccount extends PersonAccount {

    public SpeakerAccount(PersonController controller) {
        super(controller);
    }

    @Override
    public void run() {
        arrMenuOptions = new String[menuOptions.size()];
        for (int i = 0; i < menuOptions.size(); i++) {
            arrMenuOptions[i] = menuOptions.get(i);

        dropDownMenu = new JComboBox<String>(arrMenuOptions);// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
        contentPane.add(dropDownMenu);

        // Make frame
        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
        }
    }

    public void SpeEventMenu() {

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
