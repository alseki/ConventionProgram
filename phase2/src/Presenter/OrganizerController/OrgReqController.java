package Presenter.OrganizerController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;

public class OrgReqController extends SubMenu {
    protected int currentRequest;
    protected OrgReqMenu presenter;
    private String currentUserID;

    public OrgReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new OrgReqMenu(requestManager);
        this.currentUserID = currentUserID;
    }

    public void menuChoice() {
        do {
            switch (currentRequest) {
                case 0:
                    // return to main menu
                    break;
                case 1: // see the requests --> must be an organizer or an employee
                    specificRequest();
                    break;
                case 2: // fulfill requests --> must be an organizer or an employee
                    seeRequests();
                    break;
                case 3: // see a specific request
                    fulfillRequest();
                    break;
                case 4:
                    request(); // make a request
                    break;
                case 5:
                    myRequests(); // see my requests till now
                    break;
            }
        }
        while (currentRequest != 0);
    }
    private void fulfillRequest(){
        presenter.fulfillRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        requestManager.updateEntity(id);
    }
    private void seeRequests(){
        presenter.seeRequests();
    }
    private void specificRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        presenter.seeRequest(id);
    }
    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        requestManager.createRequest(this.currentUserID, content);
    }
    private void myRequests(){
        presenter.myRequests(currentUserID);
    }

    @Override
    public OrgReqMenu getPresenter() {
        return this.presenter;
    }
}
