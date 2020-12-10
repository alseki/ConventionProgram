package Presenter.OrganizerController;

import Presenter.PersonController.RequestMenuParent;
import Request.RequestManager;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class OrgReqMenu extends RequestMenuParent {

    /**
     * constructor
     * @param reqM a request manager
     */
    public OrgReqMenu(RequestManager reqM){
        super(reqM);
    }

    /**
     * getter for the menu options
     * @return the menu options
     */
    @Override
    public String[] getMenuOptions() {
        return new String[]{"View a specific request", "View requests", "Fulfill a request",
            "Make a request", "See my requests", "Modify a request"};
    }

    /**
     * see all the requests that are in the system
     */
    public String seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (String id: reqM.getAllRequestUserIds()) {
            reqs.append(reqM.getRequestStringForPerson(id));
        }
        return requestFormat() + reqs;
    }

    /**
     * fulfills request for reqId
     * @param reqId String
     * @return "The request with id" + reqId + "has been fulfilled"
     */
    public String fulfillRequestPrompt(String reqId){
        return "The request with id" + reqId + "has been fulfilled";
    }


}
