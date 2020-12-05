package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgReqController;

public class OrgReqView extends AccountView {
    OrgReqController controller;

    public OrgReqView(SubMenu controller) {
        super();
        this.controller =  (OrgReqController) controller;
    }
}
