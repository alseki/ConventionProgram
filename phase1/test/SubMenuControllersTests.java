// Programmer: Cara McNeil
// Description: Tests for Sub Menus
// Date Created: 14/11/2020
// Date Modified: 14/11/2020


import Controllers.ContactController;
import Person.AttendeeManager;
import Person.PersonManager;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

public class SubMenuControllersTests {

    // -----------------------------------------------------------------------------------------------------------------
    // AttEventController Tests

    // TODO constructor test
    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // ContactController Tests

    @Test(timeout = 50)
    public void testContactController() {
        String userID = "007";
        PersonManager personManager = new AttendeeManager();
        ContactController contactController = new ContactController(userID, personManager);
    }

    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // LoginController Tests

    // TODO constructor test
    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // MessageController Tests

    // TODO constructor test
    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // OrgEventController Tests

    // TODO constructor test
    // TODO tests for menu option methods


    // -----------------------------------------------------------------------------------------------------------------
    // SpeEventController Tests

    // TODO constructor test
    // TODO tests for menu option methods

}
