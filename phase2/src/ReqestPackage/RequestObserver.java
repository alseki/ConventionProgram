package ReqestPackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RequestObserver implements PropertyChangeListener {
    private String requestId;
    private Object status;

    public RequestObserver(String requestId){
        this.requestId= requestId;
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        status = evt.getNewValue();
    }


}
