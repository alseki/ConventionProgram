package View.Account;

import Presenter.Central.SubMenu;
import Presenter.OrganizerController.OrgPersonController;
import Presenter.OrganizerController.OrgPersonMenu;

import java.awt.*;
import java.awt.event.ActionEvent;

public class OrgPersonView extends AccountView {
    OrgPersonController controller;
    OrgPersonMenu presenter;
    String[] menuOp;

    public OrgPersonView(SubMenu controller) {
        super(controller.getPresenter());
        this.controller = (OrgPersonController) controller;
        this.presenter = (OrgPersonMenu) controller.getPresenter();

        contentPane.setBackground(new Color(92, 195, 248));

        //makeDropDownMenu(this.presenter);
    }


    private void showCreateSpe() {

    }

    private void showCreateAtt() {

    }

    private void showCreateEmp() {

    }

    private void showCreateOrg() {

    }

    private void showCancelSpe() {

    }

    private void showCancelAtt() {

    }

    private void showCancelEmp() {

    }

    private void showCancelOrg() {

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);

        if(eventName.equals(presenter.getMenuOptions()[0])) { // create speaker acc
            hideMainDropDownMenu();
            showCreateSpe();
        }

        if(eventName.equals(presenter.getMenuOptions()[1])) { // create attendee acc
            hideMainDropDownMenu();
            showCreateAtt();
        }

        if(eventName.equals(presenter.getMenuOptions()[2])) { // create employee acc
            hideMainDropDownMenu();
            showCreateEmp();
        }

        if(eventName.equals(presenter.getMenuOptions()[3])) { // create organizer acc
            hideMainDropDownMenu();
            showCreateOrg();
        }

        if(eventName.equals(presenter.getMenuOptions()[4])) { // cancel speaker acc
            hideMainDropDownMenu();
            showCancelSpe();
        }

        if(eventName.equals(presenter.getMenuOptions()[5])) { // cancel attendee acc
            hideMainDropDownMenu();
            showCancelAtt();
        }

        if(eventName.equals(presenter.getMenuOptions()[6])) { // cancel employee acc
            hideMainDropDownMenu();
            showCancelEmp();
        }

        if(eventName.equals(presenter.getMenuOptions()[7])) { // cancel organizer acc
            hideMainDropDownMenu();
            showCancelOrg();
        }
    }
}
