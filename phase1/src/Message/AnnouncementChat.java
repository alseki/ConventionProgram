package Message;

import java.util.ArrayList;
import java.util.UUID;
public class AnnouncementChat extends Chat{
    private String password;
    public AnnouncementChat(String ownerId, ArrayList<String> guestIds){
        super(ownerId, guestIds);
        this.password = UUID.randomUUID().toString();
    }

    /**
     *  a getter for the password
     * @return a String representign the annoucement Chat's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * checks if the user has the password
     * @param pass a String representing what could be the password
     * @return true iff pass is the password
     */
    public boolean checkPassword(String pass) {
        return pass.equals(password);
    }

    /**
     * adds the message id and returns true iff the pass matches the annoucement chat password
     * @param messageId a String representing the message Id
     * @param pass a string representing the password for annoucement chat
     * @return true iff the messageId was added to the list of messageid's
     */
    @Override
    public boolean addMessageId(String messageId, String pass){
        if(checkPassword(pass)){
            messageIds.add(messageId);
            return true;
        }
        return false;
    }

}
