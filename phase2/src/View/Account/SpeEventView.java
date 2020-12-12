package View.Account;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Presenter.SpeakerController.SpeEventController;
import Presenter.SpeakerController.SpeEventMenu;
import View.AccountHelpers.ListDisplayView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SpeEventView extends AccountView {
    SpeEventController controller;
    SpeEventMenu presenter;
    private JLabel dialogPrompt, dialogPrompt2;
    private final JTextField inputField = new JTextField(20);
    private JButton selectButton, announcementButton;
    private JTextArea messageField;
    JComboBox<String> eventOptions;
    private final String[] menuOp;

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

        setupPane();

    }

    private void setupPane() {
        eventOptions = new JComboBox<>(presenter.getEventOptions());
        initializeObject(eventOptions);
        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);

        initializeObject(inputField);

        dialogPrompt2 = new JLabel("");
        initializeObject(dialogPrompt2);

        messageField = new JTextArea(5, 20);
        messageField.setPreferredSize(new Dimension(20, 20));
        messageField.setLineWrap(true);
        messageField.setWrapStyleWord(true);
        initializeObject(messageField);

        selectButton = newButton("select");
        announcementButton = newButton("send announcement");
    }

    private void showConventionEvents() {

    }

    private void showEventsSpeakingAt() {
        try {
            new ListDisplayView(presenter.getOwnEventsTitle(), controller.getEvents());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.printException(e));
        } finally {
            showMainDropDownMenu();
        }
    }

    private void showChooseEvents() {
        dialogPrompt.setText(presenter.printEventTypePrompt());
        dialogPrompt.setVisible(true);
        eventOptions.setVisible(true);
        selectButton.setVisible(true);
        selectButton.setToolTipText("select what event(s) the announcement is for");
    }

    private void showMakeMultipleAnnouncements() {
        dialogPrompt2.setText(presenter.printContentPrompt());
        dialogPrompt2.setVisible(true);

        messageField.setVisible(true);

        announcementButton.setVisible(true);
        backButton.setVisible(true);
    }

    private void showMakeSingleAnnouncement() {
        dialogPrompt.setText(presenter.printEventNamePrompt());
        dialogPrompt.setVisible(true);

        inputField.setVisible(true);
        showMakeMultipleAnnouncements();
    }

    private void sendAnnouncement() {

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
            hideMainDropDownMenu();
            showChooseEvents();
        }

        if (eventName.equals("select")) {
            eventName = (String) eventOptions.getSelectedItem();
            assert eventName != null;
            if (eventName.equals(presenter.getEventOptions()[0])) { // All Events

            }
            if (eventName.equals(presenter.getEventOptions()[1])) { // Panel Events

            }
            if (eventName.equals(presenter.getEventOptions()[2])) { // Non Panel Events

            }
            if (eventName.equals(presenter.getEventOptions()[3])) { // Specific Event

            }
        }

        if (eventName.equals("send announcement")) {
            sendAnnouncement();
        }


    }
}
