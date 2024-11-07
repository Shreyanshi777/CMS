import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the login frame and main CMS frame
        LoginFrame loginFrame = new LoginFrame();
        MainFrame mainFrame = new MainFrame();

        // Show login frame initially
        loginFrame.setVisible(true);

        // Add login button action listener directly in the Main class
        loginFrame.getLoginButton().addActionListener(e -> {
            String userType = (String) loginFrame.getUserTypeComboBox().getSelectedItem();
            String username = loginFrame.getUsernameField().getText();
            String password = new String(loginFrame.getPasswordField().getPassword());

            // Login validation
            if ("Admin".equals(userType) && "admin".equals(username) && "admin123".equals(password)) {
                loginFrame.dispose();
                mainFrame.setVisible(true);
            } else if ("User".equals(userType) && "user".equals(username) && "user123".equals(password)) {
                loginFrame.dispose();
                mainFrame.setVisible(true);
                mainFrame.disableSettingsForUser(); // Disable settings for regular
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid login credentials");
            }
        });
    }
}
