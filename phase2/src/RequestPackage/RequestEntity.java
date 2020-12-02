package RequestPackage;

import java.util.UUID;

public class RequestEntity{
    private String requestingUserId;
    private String requestContent;
    private boolean fulfilled;
    private final String requestId;
    private String talkOrWorkshopConcerned;

    /*  Helper class for making this class as an observable  */


    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent= requestContent;
        this.requestingUserId =requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();

    }
    // this is a method option for the Employee and Attendee.
    public RequestEntity(String requestContent, String requestingUserId, String talkOrWorkshopConcerned){
        this.requestContent= requestContent;
        this.requestingUserId =requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();
        this.talkOrWorkshopConcerned = talkOrWorkshopConcerned;
    }
    public boolean getFulfilled(){
        return this.fulfilled;
    }

    public String getRequestingUserId() {
        return requestingUserId;
    }
    public String getRequestContent(){
        return requestContent;
    }

    public String getRequestId(){
        return this.requestId;
    }

    public String getTalkOrWorkshopConcerned () {
        return this.talkOrWorkshopConcerned;
    }

    public void setFulfilled(){
        this.fulfilled = true;
    }



}
