package Presenter.OrganizerController;

import Presenter.AttendeeController.AttReqController;
import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;

public class OrgReqController extends AttReqController {
    protected OrgReqMenu presenter;
    private String currentUserID;

    /**
     * constructor for OrgReqController
     * @param subMenu submenu
     * @param currentUserID String
     */
    public OrgReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu, currentUserID);
        presenter = new OrgReqMenu(requestManager, personManager);
        this.currentUserID = currentUserID;
    }

    public  String fulfillRequest(String reqId){
        requestManager.updateEntity(reqId);
        return presenter.fulfillResponse(reqId);
    }

    /**
     * getter for the presenter
     * @return this.presenter (OrgReqMenu)
     */
    @Override
    public SubMenuPrinter getPresenter() {
        return this.presenter;
    }
}
