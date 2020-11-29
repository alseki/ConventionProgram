package View.Login;

// Contributors: Sarah Kronenfeld
// Created: Nov 26 2020
// Last edit: Nov 26 2020

// Architecture Level - Presenter

import Controllers.InvalidChoiceException;
import View.View;

public interface iViewPane extends View {

    Integer getIntegerInput(String title, String message) throws InvalidChoiceException;

    Integer getIntegerInput(String title, String message, Integer[] args) throws InvalidChoiceException;

    String getStringInput(String title, String message);

    String getStringInput(String title, String message, String[] args) throws InvalidChoiceException;

    void printMessage(String title, String message);

}
