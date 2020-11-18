package Controllers;

public class InvalidChoiceException extends Exception {
    protected String type;

    public InvalidChoiceException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "Whoops! That's an invalid " + type + " choice. Please try again!";
    }

    public void printErrorMessage() {
        System.out.println(getMessage());
    }
}
