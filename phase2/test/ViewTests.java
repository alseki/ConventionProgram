import Controllers.InvalidChoiceException;
import Controllers.ViewInterface;
import View.PopoutView;

public class ViewTests {

    public static void main(String[] args) {
        ViewInterface view = new PopoutView();

        // Tests message printing
        testMessagePrint(view);

        // Tests a free input box
        System.out.println(testStringInput(view));

        // Tests Integer input boxes (incl. that they throw the right error if they have invalid input)
        System.out.println(testIntegerInput(view));

        // Same as above!
        System.out.println(testIntegerInput(view));

        // Tests an option input box
        System.out.println(testOptionBox(view));
    }

    static String testOptionBox(ViewInterface view) {
        String[] args = {"Molly", "Also Molly", "Still Molly", "Molly, of Course"};
        try {
            return view.getStringInput("Test", "Choose your favourite cat", args);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    static String testIntegerInput(ViewInterface view) {
        try {
            return view.getIntegerInput("Test", "Choose an integer").toString();
        } catch (InvalidChoiceException e) {
            return "Exception thrown!";
        }
    }

    static String testStringInput(ViewInterface view) {
        return view.getStringInput("Test", "What's your name?");
    }

    static void testMessagePrint(ViewInterface view) {
        view.printMessage("Test", "WOAH! A TEST!");
    }
}
