package View.Login;

import Controllers.InvalidChoiceException;
import View.Login.iViewPane;

import javax.swing.*;

// Contributors: Sarah Kronenfeld
// Created: Nov 26 2020
// Last edit: Nov 26 2020

// Architecture Level - UI

public class PopoutView implements iViewPane {
    @Override
    public Integer getIntegerInput(String title, String message) throws InvalidChoiceException {
        try {
            return Integer.valueOf(openChoiceBox(title, message));
        } catch (NumberFormatException e) {
            throw new InvalidChoiceException("integer");
        }
    }

    @Override
    public Integer getIntegerInput(String title, String message, Integer[] args) throws InvalidChoiceException {
        if (args != null && args.length > 0) {
            try {
                return args[multipleChoiceBox(title, message, args)];
            } catch (Exception e) {
                throw new InvalidChoiceException("choice");
            }
        }
        else {
            return getIntegerInput(title, message);
        }
    }

    @Override
    public String getStringInput(String title, String message) {
        return openChoiceBox(title, message);
    }

    @Override
    public String getStringInput(String title, String message, String[] args) throws InvalidChoiceException {
        if (args != null && args.length > 0) {
            try {
                return args[multipleChoiceBox(title, message, args)];
            } catch (Exception e) {
                throw new InvalidChoiceException("choice");
            }
        }
        else {
            return getStringInput(title, message);
        }
    }

    @Override
    public void printMessage(String title, String message) {
        JOptionPane.showConfirmDialog(null, title, message, JOptionPane.DEFAULT_OPTION);
    }



    private int multipleChoiceBox(String title, String message, Object[] args) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, args, args[0]);
    }

    private String openChoiceBox(String title, String message) {
        return JOptionPane.showInputDialog(null, message, title,
                JOptionPane.PLAIN_MESSAGE);
    }
}
