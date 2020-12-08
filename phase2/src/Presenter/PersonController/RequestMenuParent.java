package Presenter.PersonController;

import Presenter.Central.SubMenuPrinter;
import Request.RequestEntity;
import Request.RequestManager;

public abstract class RequestMenuParent implements SubMenuPrinter {
    protected RequestManager reqM;

    /**
     * constructor for request menu parent
     * @param reqM request manager object
     */
    public RequestMenuParent(RequestManager reqM){
        this.reqM=reqM;
    }

    /**
     * getter for the menu title
     * @return String
     */
    @Override
    public String getMenuTitle() {
        return "----- Request Menu -----";
    }

    public abstract String[] getMenuOptions();

    /**
     * allows you to see a format request for request with reqid
     * @param reqId String
     * @return String of formated request
     */
    public String seeRequest(String reqId){
        return requestFormat() + printRequest(reqId);

    }

    /**
     * request format
     * @return "Request Id \t" + "Requests Content \t" + "Pending/Fulfilled \t" + "requesting User Id \n"
     */
    protected String requestFormat(){
        return "Request Id \t" + "Requests Content \t" + "Pending/Fulfilled \t" + "requesting User Id \n";
    }

    /**
     * all the requests for userId
     * @param userId String
     * @return String of formated request for userid requests
     */
    public String myRequests(String userId){
        StringBuilder reqs = new StringBuilder();
        reqs.append(reqM.getRequestStringForPerson(userId));
        return requestFormat() + reqs;

    }

    /**
     * prints the requests of request with reqId
     * @param reqId string
     * @return String of request
     */
    protected String printRequest(String reqId) {
        return reqM.getStringOfRequest(reqId);
    }


    /**
     * prompt for request content
     */
    public String makeRequestPrompt(){
        return "Please enter the content of the Request.";
    }


    /**
     * prompt the get request Id for a request
     */
    public String seeSpecificRequestPrompt(String reqId){
        return "This is the request with id" + reqId;
    }


}
