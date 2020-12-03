package Person;

public class Organizer extends Attendee {
    /**
     * contructor for organizer
     * @param fullName string
     * @param username string
     * @param password string
     * @param email string
     */
    public Organizer(String fullName, String username, String password, String email){
        super(fullName, username, password, email);
        this.typePerson = 2;
    }


}
