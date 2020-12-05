package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgReqController;

import java.awt.event.ActionEvent;

public class OrgReqView extends AccountView {
    OrgReqController controller;

    public OrgReqView(SubMenu controller) {
        super();
        this.controller =  (OrgReqController) controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
