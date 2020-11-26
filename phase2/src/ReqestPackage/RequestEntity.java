package ReqestPackage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;

public class RequestEntity{
    private String requestingUserId;
    private String requestContent;
    private boolean fulfilled;
    private final String requestId;

    /*  Helper class for making this class as an observable  */


    public RequestEntity(String requestContent, String requestingUserId) {
        this.requestContent= requestContent;
        this.requestingUserId =requestingUserId;
        this.fulfilled = false;
        this.requestId = UUID.randomUUID().toString();

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

    public void setFulfilled(){
        this.fulfilled = true;
    }

}
