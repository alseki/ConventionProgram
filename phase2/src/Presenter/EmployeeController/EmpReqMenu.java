package Presenter.EmployeeController;

import Request.RequestManager;
import Presenter.PersonController.RequestMenuParent;

// Programmers:
// Description:
// Date Created:
// Date Modified: 02/12/2020

public class EmpReqMenu extends RequestMenuParent {
    protected RequestManager reqM;

    /**
     * constructor
     * @param reqM a request manager
     */
    public EmpReqMenu(RequestManager reqM) {
        super(reqM);
    }

    /**
     * getter for the menu options
     * @return the menu options
     */
    @Override
    public String[] getMenuOptions() {
        return new String[]{"Return to Main Menu", "View the Request Board", "View specific request",
                "See if a request is being looked after", "Make a request", "See my requests"};
    }

    /**
     * see all the requests that are in the system
     */
    public String seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (String id: reqM.getAllRequestUserIds()) {
            reqs.append(reqM.getStringOfRequest(id));
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

    /**
     * notify who is handling request request for reqId
     * @param reqId String
     * @return "The request with id" + reqId + "has been fulfilled"
     */
    public String handlingRequestPrompt(String reqId){
        return "The request with id" + reqId + "is being taken care of";
    }

}

