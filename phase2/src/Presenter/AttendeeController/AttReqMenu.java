package Presenter.AttendeeController;

import Presenter.Central.SubMenuPrinter;
import Request.RequestEntity;
import Request.RequestManager;

public class AttReqMenu implements SubMenuPrinter {
    protected RequestManager reqM;
    public AttReqMenu(RequestManager reqM){
        this.reqM=reqM;
    }

    @Override
    public String printMenuOptions() {
        System.out.println("\n----- Requests Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To make a request, Enter '1'.");
        System.out.println("To see information about a specific request, Enter 2");
        return "";
    }

    /**
     * prompt for request content
     */
    public void makeRequestPrompt(){
        System.out.println("Please enter the content of the Request.");
    }

    /**
     * prompt the enter userID
     */
    public void enterIdPrompt(){
        System.out.println("please enter your user Id");
    }

    /**
     * prompt the get request Id for a request
     */
    public void seeSpecificRequestPrompt(){
        System.out.println("Please enter the request ID for the request you would like to see;");
    }


    /**
     * to see the request for request with reqId
     * @param reqId String represetning the reqId
     */
    public void seeRequest(String reqId){
        RequestEntity req = reqM.getRequestEntity(reqId);
        System.out.println(requestFormat() + printRequest(req));

    }

    private StringBuilder printRequest(RequestEntity req) {
        StringBuilder reqs = new StringBuilder();
        reqs.append("\n").append(req.getRequestContent());
        reqs.append("\t").append(req.getRequestId());
        if (req.getFulfilled()) {
            reqs.append("\t").append("Fulfilled");

        } else {
            reqs.append("\t").append("Pending");
        }
        return reqs;
    }
    private String requestFormat(){
        return "Outstanding Request\n" + "Requests Content \t" + "Requests Id\n";
    }
}


