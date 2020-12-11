package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.SpeakerController.SpeEventController;
import Presenter.SpeakerController.SpeEventMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpeEventView extends AccountView {
    SpeEventController controller;
    SpeEventMenu presenter;
    private JLabel dialog = new JLabel("");
    private JTextField inputField = new JTextField(20);
    JComboBox<String> eventOptions;
    private String[] menuOp;

    /**
     * The view for speaker users to see their convention event options.
     * @param controller SpeEventController for handling user input
     */
    public SpeEventView(SubMenu controller) {
        super(controller);
        this.controller = (SpeEventController) controller;
        this.presenter = (SpeEventMenu) controller.getPresenter();

        contentPane.setBackground(new Color(255, 10, 190));
        menuOp = presenter.getMenuOptions();

        eventOptions = new JComboBox<>(presenter.getEventOptions());




    }

    private void setupMakeAnnouncement() {

    }

    private void showConventionEvents() {

    }

    private void showEventsSpeakingAt() {
        try {
            new ListDisplayView(presenter.getOwnEventsTitle(), controller.getEvents());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        } finally {
            showMainDropDownMenu();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if (eventName.equals(menuOp[0])) { // View convention Event list
            hideMainDropDownMenu();
            showConventionEvents();

        }
        if (eventName.equals(menuOp[1])) { // View user events
            hideMainDropDownMenu();
            showEventsSpeakingAt();
        }
        if (eventName.equals(menuOp[2])) { // Make announcement


        }


    }
}
