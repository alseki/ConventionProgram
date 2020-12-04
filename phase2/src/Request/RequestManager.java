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
     * creates a request: 2nd type
     * @param reqUserId the user who is making the request
     * @param reqContent string fro the content of the request
     * @param eventsConcerned array of strings if user making request wants to specify the event or room in the heading
     * @return true
     */
    public boolean createRequest(String reqUserId, String reqContent, String eventsConcerned){
        RequestEntity req = new RequestEntity(reqContent, reqUserId, eventsConcerned);
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
    public RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }

    public ArrayList<RequestEntity> getAllRequests() {
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
    public ArrayList<RequestEntity> getRequestsForPersonId(String personId){
        ArrayList<RequestEntity> lst = new ArrayList<>();
        for(RequestEntity req: this.requestsList){
            if(req.getRequestingUserId().equals(personId)){
                lst.add(req);
            }
        }
        return lst;
    }

    /**
     * get the string format of request with request id
     * @param reqId string representing the request id
     * @return string format of request entity
     */

    public String getStringOfRequest(String reqId){
        RequestEntity req = getRequestEntity(reqId);
        return req.toString();

    }
    public ArrayList<String> getAllRequestUserIds(){
        ArrayList<String> lst = new ArrayList<>();
        for(RequestEntity req: requestsList){
            lst.add(req.getRequestingUserId());
        }
        return lst;
    }

}




