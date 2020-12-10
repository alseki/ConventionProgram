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

    public AttReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttReqController) controller;
        this.presenter = ((AttReqController) controller).getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(new Color(100, 200, 232));

        dialogPrompt = new JLabel("");
        initializeObject(dialogPrompt);
    }


    private void showMakeReq() {
        dialogPrompt.setText(presenter.makeRequestPrompt());
        dialogPrompt.setVisible(true);
    }

    private void showViewReqInfo() {
        String reqs = this.controller.myRequests();
    }

    private void showMyReq() {

    }

    private void modifyReq() {

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        // [0] = make a request
        // [1] = view info about a request
        // [2] = show my requests
        // [3] = modify a request
        if(eventName.equals(menuOp[0])) {
            showMakeReq();
        }

        if(eventName.equals(menuOp[1])) {
            showViewReqInfo();
        }

        if(eventName.equals(menuOp[2])) {
            showMyReq();
        }

        if(eventName.equals(menuOp[3])) {
            modifyReq();
        }
    }
}
// ignore this comment