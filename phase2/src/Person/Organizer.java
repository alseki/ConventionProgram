package Person;

public class Organizer extends Attendee {
    /**
     * constructor for an attendee
     * @param fullName String
     * @param username String
     * @param password String
     * @param email String
     */
    public Organizer(String fullName, String username, String password, String email){
        super(fullName, username, password, email);
        this.typePerson = 2;
    }


}
