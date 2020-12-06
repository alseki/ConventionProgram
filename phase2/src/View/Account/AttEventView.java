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

    JComboBox<String> dropDownMenu;
    JLabel introMessage, savedMessage;
    JButton continueButton;

    public AttEventView(SubMenu controller) {
        super();
        this.controller = (AttEventController) controller;
        this.presenter = (AttEventMenu) controller.getPresenter();
    }

    private void setupEventMenuOptions() {
        dropDownMenu = new JComboBox<>(presenter.getMenuOptions());// Generates dropdown menu
        dropDownMenu.setAlignmentX(JComboBox.LEFT_ALIGNMENT);
        dropDownMenu.setSelectedIndex(0);
        dropDownMenu.addActionListener(this);
        contentPane.add(dropDownMenu);
        dropDownMenu.setVisible(false);
    }

    /*
    private void setupViewAllEvents() {

    }
     */


    /**
     * Sets up the page
     */
    public void run () {
        frame = new JFrame(this.presenter.getMenuTitle());// Create and set up the frame
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));//Sets size of frame
        contentPane.setBackground(new Color(255, 255, 200));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        continueButton = newButton("continue");
        contentPane.add(continueButton);

        setupEventMenuOptions();
        //setupViewAllEvents();
        //setupSignUpEvent();
        //setupCancelFromEvent();
        //setupGetSignedUpEvents();

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();
        if(eventName.equals(presenter.getMenuOptions()[0])) { // return to Att main menu
            //return to main menu
        }

        if(eventName.equals(presenter.getMenuOptions()[1])) { // view list of all Events
            //view all events
        }

        if(eventName.equals(presenter.getMenuOptions()[2])) { // sign up for an Event
            //sign up for event
        }

        if(eventName.equals(presenter.getMenuOptions()[3])) { // cancel your spot from an Event
            //cancel spot from event
        }

        if(eventName.equals(presenter.getMenuOptions()[4])) { // get list of your signed up events
            //view list of your signed up events
        }
    }

    private JButton newButton(String title) {
        JButton newButton = new JButton(title);
        newButton.setLocation(0, 0);
        newButton.setActionCommand(title);
        newButton.addActionListener(this);
        return newButton;
    }
}
