package View.Account;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView implements ActionListener {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();

    public AccountView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
