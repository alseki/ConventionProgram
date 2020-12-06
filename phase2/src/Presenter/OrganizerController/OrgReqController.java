package Presenter.OrganizerController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.InvalidChoiceException;

public class OrgReqController extends SubMenu {
    protected int currentRequest;
    protected OrgReqMenu presenter;
    private String currentUserID;

    public OrgReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new OrgReqMenu(requestManager);
        this.currentUserID = currentUserID;
    }

    private void fulfillRequest(String reqId){
        presenter.fulfillRequestPrompt(reqId);
        //String id = "";//SubMenu.readInput(input);
        requestManager.updateEntity(reqId);
    }
    private void seeRequests(){
        presenter.seeRequests();
    }
    private void specificRequest(String reqID) throws InvalidChoiceException {
        if (requestManager.requestExists(reqID)) {
            presenter.seeSpecificRequestPrompt(reqID);
            //String id = "";//SubMenu.readInput(input);
            //presenter.seeRequest(id);
        }
        else{
            throw new InvalidChoiceException("request");
        }
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
