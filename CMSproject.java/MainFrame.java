import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JButton settingsBtn;

    public MainFrame() {
        setTitle("CMS - Blog Management");
        setSize(1200, 800); // Set a larger frame size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 800));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(66, 135, 245)); // Set sidebar background to blue
        
        JLabel titleLabel = new JLabel("Blog", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Distinct font and larger size for the title
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);        
        sidebar.add(titleLabel);

        sidebar.add(Box.createRigidArea(new Dimension(0, 50))); // Add spacing below title

        // Sidebar Buttons
        JButton dashboardBtn = new JButton("Dashboard");
        JButton mediaBtn = new JButton("Media");
        JButton postOptionsBtn = new JButton("Post Options");  // New button for PostOptionsPanel
        settingsBtn = new JButton("Settings");

        // Array of sidebar buttons for styling
        JButton[] sidebarButtons = {dashboardBtn, mediaBtn, postOptionsBtn, settingsBtn};
        for (JButton btn : sidebarButtons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setForeground(Color.WHITE); // Set font color to white
            btn.setBackground(new Color(66, 135, 245)); // Match background color to sidebar
            btn.setFocusPainted(false); // Remove focus border
            btn.setBorderPainted(false); // Hide button border
            btn.setFont(new Font("Arial", Font.BOLD, 18)); // Make font bold and increase size
            sidebar.add(btn);
            sidebar.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between buttons
        }

        add(sidebar, BorderLayout.WEST);

        // Content Panel (central area)
        contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.WHITE); // Set central content area to white
        contentPanel.add(new DashboardPanel(), "Dashboard");
        contentPanel.add(new MediaManagementPanel(), "MediaManagement");
        contentPanel.add(new SettingsPanel(), "Settings");
        contentPanel.add(new PostOptionsPanel(), "Post");  // Add PostOptionsPanel here
        add(contentPanel, BorderLayout.CENTER);

        // Sidebar button actions
        dashboardBtn.addActionListener(e -> showPanel("Dashboard"));
        mediaBtn.addActionListener(e -> showPanel("MediaManagement"));
        postOptionsBtn.addActionListener(e -> showPanel("Post"));  // Show PostOptionsPanel when clicked
        settingsBtn.addActionListener(e -> showPanel("Settings"));
    }

    // Method to switch content panel
    public void showPanel(String name) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        cl.show(contentPanel, name);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public void disableSettingsForUser() {
        settingsBtn.setEnabled(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
