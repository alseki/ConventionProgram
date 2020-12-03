package Request.controllers;

import Presenter.Central.SubMenu;
import Request.presenters.OrgReqMenu;

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
                case 1: // see the requests --> must be an organizer
                    seeRequests();
                    break;
                case 2: // fulfill requests --> must be an organizer
                    fulfillRequest();
                    break;
                case 3: // see a sepcific request
                    specificRequest();
                    break;
                case 4:
                    request();
                    break;
                case 5:
                    myRequests();
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

}
