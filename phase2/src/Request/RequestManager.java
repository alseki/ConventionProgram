package Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestManager implements Serializable {
    private ArrayList<RequestEntity> requestsList;
    private Map<String, RequestEntity> idToRequest;

    /**
     * constructor for the request manager
     */
    public RequestManager(){
        this.requestsList = new ArrayList<>();
        idToRequest = new HashMap<>();

    }
    private void addRequest(RequestEntity req){
        requestsList.add(req);
    }

    /**
     * creates a reauest
     * @param reqUserId the user who is making the request
     * @param reqContent string fro the content of the request
     * @return true
     */
    public boolean createRequest(String reqUserId, String reqContent){
        RequestEntity req = new RequestEntity(reqContent, reqUserId);
        updateMap(reqUserId, req);
        addRequest(req);
        return true;
    }

    /**
     * allows user to change/modify the request that is still pending
     * @param reqUserId the user who is changing the request
     * @param reqId of the request content to be brought into a string
     * @return true
     */
    public boolean modifyRequest(String reqUserId, String reqId) {
        RequestEntity req = getRequestEntity(reqId);
        if(!req.getFulfilled()) {
            String oldRequest = getStringOfRequest(reqId);
            createRequest(oldRequest, reqUserId);
        }
        return true;

    }


    private void updateMap(String str, RequestEntity req){
        idToRequest.put(str, req);
    }

    private RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }

    private ArrayList<RequestEntity> getAllRequests() {
        return requestsList;
    }



    /**
     * to update an entity
     * @param reqId string for request id
     */
    public void updateEntity(String reqId){
        RequestEntity req = getRequestEntity(reqId);
        req.setFulfilled();
    }

    /**
     * allows you to get all the requests for the person with person id
     * @param personId String
     * @return format of requests for all the requests for this person.
     */
    public String getRequestStringForPerson(String personId){
        StringBuilder reqs = new StringBuilder();
        for(RequestEntity req: this.requestsList){
            if(req.getRequestingUserId().equals(personId)){
                reqs.append(req.toString());
            }
        }
        return reqs.toString();
    }

    /**
     * get the string format of request with request id
     * @param reqId string representing the request id
     * @return string format of request entity
     */

    public String getStringOfRequest(String reqId) {
        RequestEntity req = getRequestEntity(reqId);
        return req.toString();

    }

    /**
     * way to get all the requsting user id's
     * @return ArrrayLis of requesting user id's
     */
    public ArrayList<String> getAllRequestUserIds(){
        ArrayList<String> lst = new ArrayList<>();
        for(RequestEntity req: getAllRequests()){
            lst.add(req.getRequestingUserId());
        }
        return lst;
    }

    /**
     * lets you know if a request with reqId exists
     * @param reqID String
     * @return true iff the requests sexists
     */
    public boolean requestExists(String reqID){
        return idToRequest.containsKey(reqID);
    }

}




