package ReqestPackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestManager {
    private ArrayList<RequestEntity> requestsList;
    private Map<String, RequestEntity> idToRequest;

    public RequestManager(){
        this.requestsList = new ArrayList<RequestEntity>();
        idToRequest = new HashMap<String, RequestEntity>();

    }

    private void addRequest(RequestEntity req){
        requestsList.add(req);
    }

    /**
     * creates a request entity.
     * @param reqUserId String represenitng the requesting userId
     * @param reqContent String reprsenting the request content
     * @return true;
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
     * allows one to get a certain request object
     * @param reqId a String representing the reqId
     * @return RequestEntity with reqID
     */
    public RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }

    /**
     * changes the fulifilled status the request entity with id reqId
     * @param reqId String reprsenting the reqID
     */
    public void updateEntity(String reqId) {
        RequestEntity req = getRequestEntity(reqId);
        req.setFulfilled();

    }

    /**
     * the list of all Request entities
     * @return ArrayList<RequestEntity>
     */
    public ArrayList<RequestEntity> getAllRequests(){
        return requestsList;

    }

    /**
     * adds an observer </prop> to the requestentity with Id request ID
     * @param requestId String of requestId
     * @param prop a propertychanglistner ( for us it's only requestmanager and attendee)
     */
    public void addObserver(String requestId, PropertyChangeListener prop){
        // TODO will need to do this at the controller level.
        RequestEntity req = getRequestEntity(requestId);
        req.addObserver(prop);
    }


}
