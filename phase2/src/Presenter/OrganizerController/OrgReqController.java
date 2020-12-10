package Presenter.OrganizerController;

import Presenter.Central.SubMenu;
import Presenter.Exceptions.InvalidChoiceException;
import Request.RequestEntity;
import Request.RequestManager;

import java.util.ArrayList;

public class OrgReqController extends SubMenu {
    protected int currentRequest;
    protected OrgReqMenu presenter;
    private String currentUserID;
    private ArrayList<RequestEntity> requestEntities;
    protected RequestManager requestManager;

    /**
     * constructor for OrgReqController
     * @param subMenu submenu
     * @param currentUserID String
     */
    public OrgReqController(SubMenu subMenu, String currentUserID) {
        super(subMenu);
        this.presenter = new OrgReqMenu(requestManager);
        this.currentUserID = currentUserID;
        this.requestManager = requestManager;
    }

    private String fulfillRequest(String reqId){
        requestManager.updateEntity(reqId);
        return presenter.fulfillRequestPrompt(reqId);
        //String id = "";//SubMenu.readInput(input);

    }

    /**
     *
     * @param req
     * @param requests
     */
    public void removeRequest(RequestEntity req, ArrayList<RequestEntity> requests) {
        requests.remove(req);
    }

    private String seeRequests(){
        return presenter.seeRequests();
    }
    private String specificRequest(String reqID) throws InvalidChoiceException {
        if (requestManager.requestExists(reqID)) {
            return presenter.seeSpecificRequestPrompt(reqID);
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

    /**
     * getter for the presenter
     * @return this.presenter (OrgReqMenu)
     */
    @Override
    public OrgReqMenu getPresenter() {
        return this.presenter;
    }
}
