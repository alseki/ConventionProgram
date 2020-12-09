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

    public AttReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (AttReqController) controller;
        this.presenter = ((AttReqController) controller).getPresenter();

        menuOp = this.presenter.getMenuOptions();

        contentPane.setBackground(new Color(100, 200, 232));
    }


    private void showMakeReq() {

    }

    private void showViewReqInfo() {

    }

    private void showMyReq() {

    }

    private void modifyReq() {

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        String eventName = event.getActionCommand();

        // [1] = make a request
        // [2] = view info about a request
        // [3] = show my requests
        // [4] = modify a request
        if(eventName.equals(menuOp[1])) {
            showMakeReq();
        }

        if(eventName.equals(menuOp[2])) {
            showViewReqInfo();
        }

        if(eventName.equals(menuOp[3])) {
            showMyReq();
        }

        if(eventName.equals(menuOp[4])) {
            modifyReq();
        }
    }
}
