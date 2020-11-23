package ReqestPackage;

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
    public boolean createRequest(String reqUserId, String reqContent){
        RequestEntity req = new RequestEntity(reqContent, reqUserId);
        updateMap(reqUserId, req);
        addRequest(req);
        return true;
    }
    private void updateMap(String str, RequestEntity req){
        idToRequest.put(str, req);
    }
    public RequestEntity getRequestEntity(String reqId){
        return idToRequest.get(reqId);
    }
    public boolean updateEntity(String reqId) {
        RequestEntity req = getRequestEntity(reqId);
        req.setFulfilled();
        return true;
    }
    public ArrayList<RequestEntity> getAllRequests(){
        return requestsList;

    }
}
