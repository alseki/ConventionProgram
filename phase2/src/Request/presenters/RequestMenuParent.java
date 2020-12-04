package Request.presenters;

import Presenter.Central.SubMenuPrinter;
import Request.RequestManager;

public abstract class RequestMenuParent implements SubMenuPrinter {
    protected RequestManager reqM;
    RequestMenuParent(RequestManager reqM){
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
        for(String str: reqM.getAllRequestUserIds()){
            if(printRequest(str).contains(userId)){
                reqs.append(printRequest(str));
            }
        }
        return requestFormat() + reqs;

    }
    protected String printRequest(String reqid) {
        return reqM.getStringOfRequest(reqid);
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
    public String seeSpecificRequestPrompt(){
        return "Please enter the request ID for the request you would like to see;";
    }

}
