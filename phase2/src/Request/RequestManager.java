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


    private void updateMap(String str, RequestEntity req){
        idToRequest.put(str, req);
    }

    /**
     * get a specific entity with reqid
     * @param reqId string for request id
     * @return the request entity with request id
     */
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
    public boolean requestExists(String reqID){
        return idToRequest.containsKey(reqID);
    }

}




