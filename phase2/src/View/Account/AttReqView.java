package View.Account;

import Presenter.AttendeeController.AttReqController;
import Presenter.AttendeeController.AttReqMenu;
import Presenter.Central.SubMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AttReqView extends AccountView {
    AttReqController controller;
    AttReqMenu presenter;
    String[] menuOp;
    JLabel dialogPrompt;

    /**
     * The view for attendee users to see their request options.
     * @param controller AttReqController for handling user input
     */
    public AttReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttReqController) controller;
        this.presenter = (AttReqMenu) controller.getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(new Color(100, 200, 232));

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);
    }


    private void showMakeReq() {
        backButton.setVisible(true);

        dialogPrompt = new JLabel(this.controller.makeRequestPrompt());
        dialogPrompt.setVisible(true);
    }

    private void showViewReqInfo() {
        backButton.setVisible(true);

        String reqs = this.controller.myRequests();
    }

    private void showMyReq() {
        backButton.setVisible(true);

    }

    private void modifyReq() {
        backButton.setVisible(true);

    }


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
            showViewReqInfo();
        }

        if(eventName.equals(menuOp[2])) {
            hideMainDropDownMenu();
            showMyReq();
        }

        if(eventName.equals(menuOp[3])) {
            hideMainDropDownMenu();
            modifyReq();
        }
    }
}
// ignore this comment