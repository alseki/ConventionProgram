package Controllers;

public class NoDataException extends InvalidChoiceException {

    public NoDataException(String type) {
        super(type);
    }

    @Override
    public String getMessage() {
        return "Whoops! No " + type + "s exist. Please try again!";
    }

    public void printErrorMessage() {
        System.out.println(getMessage());
    }
}
