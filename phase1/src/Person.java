import java.util.ArrayList;
import java.util.UUID;

    public abstract class Person {
        private String username;
        private String Id;
        private String password;
        private String email;
        private String fullName;
        private ArrayList<String> contactList;
        private ArrayList<UUID> chatId;

        /**
         * this allows for access to the persons; username
         *
         * @return the string of the username
         */
        public String getUsername() {
            return this.username;
        }

        /**
         * This gives access to the person's ID
         *
         * @return returns the string of the ID
         */
        public String getId() {
            return this.Id;
        }

        /**
         * this gives access to the person's email
         *
         * @return returns this person's email
         */

        public String getEmail() {
            return this.email;
        }

        /**
         * this gives access to the person's full anme
         *
         * @return returns this person's full name
         */

        public String getFullName() {
            return this.fullName;
        }

        /**
         * this gives access to the person's password
         *
         * @return returns this person's password
         */

        public String getPassword() {
            return this.password;
        }

        /**
         * this returns the names of the person's in the contact list
         *
         * @return this returns the names in the contact lsit will return and array list of string's
         */
        public ArrayList<String> getContactList() {
            return this.contactList;
        }

        /**
         * set's the email of the person. Should be in the format of __@__.com. The email should be a string
         *
         * @param email the email of the person
         */

        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * allows to set the person's username. Username should be a string
         *
         * @param username the username of the person
         */

        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * allows to set the person's password. The password should be a string.
         *
         * @param password the password of the person
         */

        public void setPassword(String password) {
            this.password = password;
        }
    }

