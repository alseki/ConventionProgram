package Person;

import java.util.ArrayList;

public class Admin extends Person {

    protected ArrayList<String> emptyEventIDs = new ArrayList<>();
    protected ArrayList<String> emptyChatIDs = new ArrayList<>();
    // protected ArrayList<String> inactiveAccounts = new ArrayList<>();

    public Admin(String adminUN, String adminPW) {
        this.username = adminUN;
        this.password = adminPW;
        this.typePerson = 4;
    }

    public ArrayList<String> getEmptyEventIDs() {
        return emptyEventIDs;
    }

    public ArrayList<String> getEmptyChatIDs() {
        return emptyChatIDs;
    }

    public void addEmptyEventToList(String eventID) {
        emptyEventIDs.add(eventID);
    }

    public void addEmptyChatToList(String chatID) {
        emptyChatIDs.add(chatID);
    }
}
