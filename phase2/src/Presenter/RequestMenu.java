package Presenter;

import Controllers.NoDataException;
import Events.EventManager;
import Events.RoomManager;
import Person.PersonManager;
import ReqestPackage.RequestEntity;
import ReqestPackage.RequestManager;

public class RequestMenu implements printSubMenu{
    protected RequestManager reqm;
    public RequestMenu(RequestManager reqm){
        this.reqm=reqm;
    }

    @Override
    public void printMenuOptions() {
        System.out.println("\n----- Requests Menu -----");
        System.out.println("To return to Main Menu, Enter '0'.");
        System.out.println("To make a request, Enter '1'.");
        System.out.println("To see outstanding requests, Enter '2'."); // must be organizer
        System.out.println("To fulfill a request, Enter '3'. "); // must be organizer

    }
    public void makeRequestPrompt(){
        System.out.println("Please enter the content of the Request.");
    }
    public void enterIdPrompt(){
        System.out.println("please enter your user Id");
    }

    public void seeOutstandingRequests() {
        StringBuilder reqs = new StringBuilder();
        for (RequestEntity r : reqm.getAllRequests()) {
            reqs.append("\n").append(r.getRequestContent());
            reqs.append("\t").append(r.getRequestId());
        }
        System.out.println("Outstanding Request\n" + "Requests Content \t" + "Requests Id\n" + reqs);
    }
    public void fulfillRequestPrompt(){
        System.out.println("Which Request Would you like to fulfill, please enter the ID.");
    }



    }



}
