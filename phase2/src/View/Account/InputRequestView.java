package View.Account;

import Presenter.Exceptions.InvalidChoiceException;
import Presenter.PersonController.MessageController;
import Presenter.PersonController.MessageMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputRequestView implements ActionListener {

    JButton submitButton;
    JLabel promptViewChat;
    JLabel promptArchiveChat;
    JLabel promptExitChat;
    JFrame frame;
    JPanel contentPane;
    //JTextField textField;
    int operation;
    MessageController controller;
    MessageMenu presenter;
    JLabel dialogPrompt, dialogPrompt2;
    JButton sendMsg, chooseChat;
    ListDisplayView msgList;
    JTextField inputField;
    JTextArea messageField;

    public InputRequestView(String title, int operation){
        this.operation = operation;
        frame = new JFrame(title); // Create and set up the frame
        contentPane = new JPanel();// Create a content pane with a BoxLayout and empty borders
        contentPane.setPreferredSize(new Dimension(400,100));
        contentPane.setBackground(new Color(255, 255, 255));// Sets background colour to white
        contentPane.setLayout(new FlowLayout());

        JTextField textField = new JTextField(16);
        contentPane.add(textField);

        submitButton = new JButton("Submit");
        //submitButton.setBounds(10, 600, 80, 30);
        submitButton.setActionCommand("submit");
        submitButton.addActionListener(this);
        submitButton.setToolTipText("click this button to submit the input text");
        contentPane.add(submitButton);
        submitButton.setVisible(true);

        frame.setContentPane(contentPane);// Add content pane to frame
        frame.pack();// Size and then display the frame.
        display();
    }

    private void viewMessagesInChat() throws InvalidChoiceException {
        msgList = new ListDisplayView("-- MESSAGES --", presenter.getChats(presenter.getChatList()));

    }

    /**
     * Shows every component stored in contentPane
     */
    private void display() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(true);
        }
        frame.setVisible(true);
    }

    /**
     * Hides every component stored in contentPane
     */
    void hide() {
        for (Component item: contentPane.getComponents()) {
            item.setVisible(false);
        }
        frame.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equals("submit") && this.operation == 1) {
            hide();
            try {
                viewMessagesInChat();
            } catch (InvalidChoiceException e) {
                e.printStackTrace();
            }
        }
    }
}
