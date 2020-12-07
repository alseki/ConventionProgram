package View.Account;

import Presenter.AttendeeController.AttEventController;
import Presenter.AttendeeController.AttEventMenu;
import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttEventView extends AccountView {
    AttEventController controller;
    AttEventMenu presenter;

    JLabel enterEventNameMsg, allEvents, yourEvents;
    JButton continueButton, okayButton, backButton, signupButton, cancelSpotButton;
    JTextField inputEventName;

    public AttEventView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
        this.presenter = (AttEventMenu) controller.getPresenter();


        frame = new JFrame(this.presenter.getMenuTitle());// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 200));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        continueButton = newButton("continue");
        contentPane.add(continueButton);

        backButton = newButton("back");
        contentPane.add(backButton);

        setupEventMenuOptions();
        setupViewAllEvents();
        setupSignUpEvent();
        setupCancelFromEvent();
        setupGetYourEvents();

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    private void setupEventMenuOptions() {
        makeDropDownMenu(presenter);
    }

    private void setupViewAllEvents() {
        allEvents = new JLabel();//Create and add label that is centered and has empty borders
        allEvents.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        allEvents.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(allEvents);
        allEvents.setVisible(false);

        okayButton = newButton("okay");
        contentPane.add(okayButton);
        okayButton.setVisible(false);

        // return to AttEventMenu button
    }

    private void setupSignUpEvent() {
        enterEventNameMsg = new JLabel(this.presenter.printAddEventPrompt());
        contentPane.add(enterEventNameMsg);
        enterEventNameMsg.setVisible(false);

        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        signupButton = newButton("signup");
        contentPane.add(signupButton);
        signupButton.setVisible(false);

        // also a "return to AttEventMenu" button
    }

    private void setupCancelFromEvent() {
        enterEventNameMsg = new JLabel(this.presenter.printRemoveEventPrompt());
        contentPane.add(enterEventNameMsg);
        enterEventNameMsg.setVisible(false);

        inputEventName = new JTextField(40);
        contentPane.add(inputEventName);
        inputEventName.setVisible(false);

        cancelSpotButton = newButton("cancelSpot");
        contentPane.add(cancelSpotButton);
        cancelSpotButton.setVisible(false);

        // and maybe also a "return to AttEventMenu" button
    }

    private void setupGetYourEvents() {
        yourEvents = new JLabel();//Create and add label that is centered and has empty borders
        yourEvents.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        yourEvents.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        contentPane.add(yourEvents);
        yourEvents.setVisible(false);
    }

    @Override
    public void showMainDropDownMenu() {
        super.showMainDropDownMenu();
        continueButton.setVisible(true);
    }

    @Override
    public void hideMainDropDownMenu() {
        super.hideMainDropDownMenu();
        continueButton.setVisible(false);
    }

    private void showViewAllEvents() {
        allEvents.setVisible(true);
        okayButton.setVisible(true);
    }

    private void showSignUp() {
        enterEventNameMsg.setVisible(true);
        inputEventName.setVisible(true);
        signupButton.setVisible(true);
        // set Go Back button to visible
    }

    private void showCancelSpot() {
        enterEventNameMsg.setVisible(true);
        inputEventName.setVisible(true);
        cancelSpotButton.setVisible(true);
        // set Go Back button to visible
    }

    private void showYourEvents() {
        yourEvents.setVisible(true);
        okayButton.setVisible(true);
    }


    private void signup() {
        String event = inputEventName.getText();

        //controller.signupForEvent(event);
    }

    private void cancelSpot() {
        String event = inputEventName.getText();

        //controller.cancelSpotFromEvent(event);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();


        // if statements for when "okay" and continue buttons are pressed


        if(eventName.equals("back")) { //can probably combine this with the next if statement
            showMainDropDownMenu();
        }

        if(eventName.equals(presenter.getMenuOptions()[0])) { // return to Att main menu
            showMainDropDownMenu();
        }

        if(eventName.equals(presenter.getMenuOptions()[1])) { // view list of all Events
            hideMainDropDownMenu();
            showViewAllEvents();
        }

        if(eventName.equals(presenter.getMenuOptions()[2])) { // sign up for an Event
            hideMainDropDownMenu();
            showSignUp();
        }

        if(eventName.equals(signupButton.getActionCommand())) {
            signup();
        }

        if(eventName.equals(presenter.getMenuOptions()[3])) { // cancel your spot from an Event
            hideMainDropDownMenu();
            showCancelSpot();
        }

        if(eventName.equals(cancelSpotButton.getActionCommand())) {
            cancelSpot();
        }

        if(eventName.equals(presenter.getMenuOptions()[4])) { // get list of your signed up events
            hideMainDropDownMenu();
            showYourEvents();
        }
    }
}
//ignore this comment... testing
