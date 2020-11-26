package Controllers;

// Contributors: Sarah Kronenfeld
// Created: Nov 26 2020
// Last edit: Nov 26 2020

// Architecture Level - Presenter

public interface ViewInterface {

    Integer getIntegerInput(String title, String message) throws InvalidChoiceException;

    Integer getIntegerInput(String title, String message, Integer[] args) throws InvalidChoiceException;

    String getStringInput(String title, String message);

    String getStringInput(String title, String message, String[] args) throws InvalidChoiceException;

    void printMessage(String title, String message);

}
