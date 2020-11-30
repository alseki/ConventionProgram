package Presenter.OrganizerController;

import Presenter.Central.SubMenu;
import Presenter.Central.SubMenuPrinter;
import Presenter.NoDataException;
import Request.RequestEntity;
import Request.RequestManager;

public class OrgReqMenu implements SubMenuPrinter {
    private RequestManager reqM;

    public OrgReqMenu(RequestManager reqM){
        this.reqM=reqM;
    }
    @Override
    public String printMenuOptions() {
        System.out.println("\n----- Requests Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To see a specific request, Enter 1");
        System.out.println("To see requests, Enter '2'.");
        System.out.println("To fulfill a request, Enter '3'. ");
        System.out.println("To make a request, Enter '4'.");
        return "";
    }

    public void makeRequestPrompt(){
        System.out.println("Please enter the content of the Request.");
    }
    public void enterIdPrompt(){
        System.out.println("please enter your user Id");
    }

    @Override
    public void printList(String[] list, String type) throws NoDataException {

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
        /*for (RequestEntity r : reqM.getAllRequests()) { //TODO see if this violates clean architecture
            reqs.append(printRequest(r));
        }*/
        System.out.println(requestFormat() + reqs);
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
        RequestEntity req = reqM.getRequestEntity(reqId);
        System.out.println("Outstanding Request\n" + "Requests Content \t" + "Requests Id\n" + printRequest(req));

    }
    private String requestFormat(){
        return "Outstanding Request\n" + "Requests Content \t" + "Requests Id\n";
    }
}
