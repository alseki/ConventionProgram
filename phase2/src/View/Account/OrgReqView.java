package View.Account;

import Presenter.Central.SubMenu;
import Request.controllers.OrgReqController;

import javax.swing.*;

public class OrgReqView extends AccountView {
    OrgReqController controller;

    public OrgReqView(SubMenu controller) {
        super();
        this.controller =  (OrgReqController) controller;
    }
}
