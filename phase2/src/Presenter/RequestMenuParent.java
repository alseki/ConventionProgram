package Presenter;

import Presenter.Central.SubMenuPrinter;
import Request.RequestEntity;
import Request.RequestManager;

public abstract class RequestMenuParent implements SubMenuPrinter {
    protected RequestManager reqM;

    public RequestMenuParent(RequestManager reqM){
        this.reqM=reqM;
    }

    @Override
    public String getMenuTitle() {
        return "----- Request Menu -----";
    }

    public abstract String[] getMenuOptions();

    public String seeRequest(String reqId){
        return requestFormat() + printRequest(reqId);

    }
    protected String requestFormat(){
        return "Request Id \t" + "Requests Content \t" + "Pending/Fulfilled \t" + "requesting User Id \n";
    }
    public String myRequests(String userId){
        StringBuilder reqs = new StringBuilder();
        reqs.append(reqM.getRequestStringForPerson(userId));
        return requestFormat() + reqs;

    }
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
