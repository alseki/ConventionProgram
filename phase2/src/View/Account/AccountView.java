package View.Account;

import javax.swing.*;

/**
 * A view that is instantiated with a Controller and builds frame based on said Controller
 */
public abstract class AccountView {
    public JFrame frame = new JFrame();
    public JPanel contentPane = new JPanel();

    public AccountView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
