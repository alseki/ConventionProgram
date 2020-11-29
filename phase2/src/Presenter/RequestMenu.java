package Presenter;

import ReqestPackage.RequestEntity;
import ReqestPackage.RequestManager;

public class RequestMenu implements printSubMenu{
    protected RequestManager reqm;
    public RequestMenu(RequestManager reqm){
        this.reqm=reqm;
    }

    @Override
    public void printMenuOptions() { // if else for organizer/attendee different menus
        System.out.println("\n----- Requests Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To make a request, Enter '1'.");
        System.out.println("To see a specific request, Enter 2");
        System.out.println("To see requests, Enter '3'."); // must be organizer
        System.out.println("To fulfill a request, Enter '4'. "); // must be organizer

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
     * allows to see the list of all requests
     */
    public void seeRequests() {
        StringBuilder reqs = new StringBuilder();
        for (RequestEntity r : reqm.getAllRequests()) {
            reqs.append(printRequest(r));
        }
        System.out.println(requestFormat() + reqs);
    }

    /**
     * prompt to get requestId for fullfilling
     */
    public void fulfillRequestPrompt(){
        System.out.println("Which Request Would you like to fulfill, please enter the ID.");
    }

    /**
     * to see the request for request with reqId
     * @param reqId String represetning the reqId
     */
    public void seeRequest(String reqId){
        RequestEntity req = reqm.getRequestEntity(reqId);
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
