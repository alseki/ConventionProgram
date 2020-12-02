package Presenter.OrganizerController;

import Presenter.Central.SubMenuPrinter;
import Presenter.Exceptions.NoDataException;
import Request.RequestEntity;
import Request.RequestManager;

public class OrgReqMenu implements SubMenuPrinter {
    private RequestManager reqM;

    /**
     * contructor
     * @param reqM a request manager
     */
    public OrgReqMenu(RequestManager reqM){
        this.reqM=reqM;
    }

    /**
     * menu options
     * @return empty string
     */
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

    /**
     * prompt to make a request
     */

    public void makeRequestPrompt(){
        System.out.println("Please enter the content of the Request.");
    }

    /**
     * prompt for user id
     */
    public void enterIdPrompt(){
        System.out.println("please enter your user Id");
    }

    /**
     * this is just for proper inheritance
     * @param list
     * @param type
     * @throws NoDataException
     */
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
