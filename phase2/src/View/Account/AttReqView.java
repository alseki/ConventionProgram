package View.Account;

import Presenter.AttendeeController.AttReqController;
import Presenter.AttendeeController.AttReqMenu;
import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttReqView extends AccountView {
    AttReqController controller;
    AttReqMenu presenter;
    String[] menuOp;
    JLabel dialogPrompt;
    JTextField inputField;
    ListDisplayView reqsList;
    JButton submit;

    /**
     * The view for attendee users to see their request options.
     * @param controller AttReqController for handling user input
     */
    public AttReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttReqController) controller;
        this.presenter = (AttReqMenu) controller.getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(greenBG);

        submit = new JButton("submit");
        initializeObject(submit);

        initializeObject(dialogPrompt);
        initializeObject(inputField);
    }



    private void showMakeReq() {
        submit.setActionCommand("make");
        submit.setVisible(true);

        dialogPrompt = new JLabel(presenter.makeRequestPrompt());
        dialogPrompt.setVisible(true);

        inputField = new JTextField(200);
        inputField.setVisible(true);
    }

    private void makeReq() {
        dialogPrompt = new JLabel(presenter.printRequestCreated());
        dialogPrompt.setVisible(true);

        backButton.setVisible(true);
    }

    private void showChooseReqPrompt() {
        dialogPrompt = new JLabel(presenter.seeRequestPrompt());
        dialogPrompt.setVisible(true);

        inputField = new JTextField(200);
        inputField.setVisible(true);

        submit.setActionCommand("show");
        submit.setVisible(true);
    }

    private void showReq() {
        inputField.setVisible(false);

        String reqID = inputField.getText();

        try {
            backButton.setVisible(true);

            dialogPrompt = new JLabel(presenter.seeRequest(reqID));
            dialogPrompt.setVisible(true);

            frame.setTitle(presenter.seeRequestTitle(reqID));
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
            dialogPrompt.setVisible(false);
            showMainDropDownMenu();
        }
    }

    private void showMyReqs() {
        try {
            reqsList = new ListDisplayView(presenter.myRequestTitle(), presenter.myRequests());
        } catch (InvalidChoiceException e) {
            exceptionDialogBox(presenter.exceptionTitle(), presenter.printException(e));
        }
    }

    /*private void modifyReq() {
        backButton.setVisible(true);

    }*/


    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        // [0] = make a request
        // [1] = view info about a request
        // [2] = show my requests
        // [3] = modify a request
        if(eventName.equals(menuOp[0])) {
            hideMainDropDownMenu();
            showMakeReq();
        }

        if(eventName.equals(menuOp[1])) {
            hideMainDropDownMenu();
            showChooseReqPrompt();
        }

        if(eventName.equals(menuOp[2])) {
            hideMainDropDownMenu();
            showMyReqs();
        }

        if (eventName.equals("make")) {
            makeReq();
        }

        if (eventName.equals("show")) {
            showReq();
        }

        if (eventName.equals("back")) {
            showMainDropDownMenu();
        }

        /*if(eventName.equals(menuOp[3])) {
            hideMainDropDownMenu();
            modifyReq();
        }*/
    }
}
// ignore this comment