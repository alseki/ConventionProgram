package Presenter.AttendeeController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Request.RequestManager;

public class AttReqController extends SubMenu {
    protected RequestManager reqM;
    protected AttReqMenu presenter;
    private String currentUserID;

    /**
     * constructor for AttReqController
     * @param subMenu SubMenu
     * @param currentUserID string
     */
    public AttReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new AttReqMenu(reqM);
        this.currentUserID = currentUserID;
    }

    private void createRequest(String content) {
        reqM.createRequest(this.currentUserID, content);
    }

    /**
     * getter for the presenter
     * @return this.presenter (AttReqMenu)
     */
    @Override
    public SubMenuPrinter getPresenter() {
        return this.presenter;
    }
}




