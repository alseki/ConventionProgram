package Presenter.OrganizerController;

import Presenter.Central.SubMenu;

public class OrgReqController extends SubMenu {
    protected int currentRequest;
    protected OrgReqMenu presenter;
    private String currentUserID;

    /**
     * construcro roganizer equest controller
     * @param subMenu submenu
     * @param currentUserID currentuserID string
     */
    OrgReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new OrgReqMenu(requestManager);
        this.currentUserID = currentUserID;
    }

    /**
     * menuchoices
     */

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
                    getRequest();
                    break;
                case 4:
                    request();
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
    private void getRequest(){
        presenter.seeSpecificRequestPrompt();
        String id = "";//SubMenu.readInput(input);
        presenter.seeRequest(id);
    }

    private void request(){
        presenter.makeRequestPrompt();
        String content = "";//SubMenu.readInput(input);
        presenter.enterIdPrompt();
        String id = "";//SubMenu.readInput(input);
        requestManager.createRequest(id, content);
        // TODO reqM.addObserver(OrganizerManagerreqs);
        // TODO reqM.addObeserver(currentPerson)
    }


}
