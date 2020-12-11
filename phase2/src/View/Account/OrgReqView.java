package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgReqController;
import Presenter.OrganizerController.OrgReqMenu;

import java.awt.*;
import java.awt.event.ActionEvent;

public class OrgReqView extends AccountView {
    OrgReqController controller;
    OrgReqMenu presenter;

    /**
     * The view for organiser users to see their request options.
     * @param controller OrgReqController for handling user input
     */
    public OrgReqView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller =  (OrgReqController) controller;
        this.presenter = (OrgReqMenu) controller.getPresenter();

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
        super.actionPerformed(event);

    }
}
