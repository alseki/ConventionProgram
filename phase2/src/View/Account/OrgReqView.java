package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgReqController;

import java.awt.*;
import java.awt.event.ActionEvent;

public class OrgReqView extends AccountView {
    OrgReqController controller;

    public OrgReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller =  (OrgReqController) controller;

        contentPane.setBackground(new Color(100, 200, 100));
    }

    private void showViewSpecificReq() {

    }

    private void showViewReqs() {

    }

    private void showFulfillReq() {

    }

    private void makeAReq() {

    }

    private void showMyReqs() {

    }

    private void modifyReq() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();


    }
}
