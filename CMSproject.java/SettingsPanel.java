import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private JTextField siteNameField;
    private JTextField taglineField;
    private JTextField contactInfoField;
    private JComboBox<String> themeSelectorComboBox;
    private JComboBox<String> layoutSelectorComboBox;

    public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Settings and Configuration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titleLabel);

        add(Box.createRigidArea(new Dimension(0, 20)));

        // Font for text fields (3-4 points larger than default)
        Font textFieldFont = new Font("Arial", Font.PLAIN, 18);

        // General Site Settings Section
        JPanel generalSettingsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        generalSettingsPanel.setBorder(BorderFactory.createTitledBorder("General Site Settings"));

        generalSettingsPanel.add(new JLabel("Site Name:"));
        siteNameField = new JTextField(25);
        siteNameField.setFont(textFieldFont);  // Apply custom font to siteNameField
        generalSettingsPanel.add(siteNameField);

        generalSettingsPanel.add(new JLabel("Tagline:"));
        taglineField = new JTextField(25);
        taglineField.setFont(textFieldFont);  // Apply custom font to taglineField
        generalSettingsPanel.add(taglineField);

        generalSettingsPanel.add(new JLabel("Contact Information:"));
        contactInfoField = new JTextField(25);
        contactInfoField.setFont(textFieldFont);  // Apply custom font to contactInfoField
        generalSettingsPanel.add(contactInfoField);

        add(generalSettingsPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Theme and Layout Management Section
        JPanel themeLayoutPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        themeLayoutPanel.setBorder(BorderFactory.createTitledBorder("Theme and Layout Management"));

        themeLayoutPanel.add(new JLabel("Select Theme:"));
        themeSelectorComboBox = new JComboBox<>(new String[]{"Light", "Dark", "classic","minimal"});
        themeLayoutPanel.add(themeSelectorComboBox);

        themeLayoutPanel.add(new JLabel("Select Layout:"));
        layoutSelectorComboBox = new JComboBox<>(new String[]{"Default", "Grid", "Masonry"});
        themeLayoutPanel.add(layoutSelectorComboBox);

        add(themeLayoutPanel);
        add(Box.createRigidArea(new Dimension(0, 20)));

        // Save Button
        JButton saveButton = new JButton("Save Settings");
        saveButton.setFont(new Font("Sans Serif", Font.BOLD, 16));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setBackground(new Color(50, 115, 220));
        saveButton.setForeground(Color.WHITE);

        // Action Listener for Save Button
        saveButton.addActionListener(e -> saveSettings());
        add(saveButton);
    }

    // Method to handle saving settings (could include database/storage integration here)
    private void saveSettings() {
        String siteName = siteNameField.getText();
        String tagline = taglineField.getText();
        String contactInfo = contactInfoField.getText();
        String selectedTheme = (String) themeSelectorComboBox.getSelectedItem();
        String selectedLayout = (String) layoutSelectorComboBox.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Settings saved successfully!\n"
                + "Site Name: " + siteName + "\n"
                + "Tagline: " + tagline + "\n"
                + "Contact Info: " + contactInfo + "\n"
                + "Selected Theme: " + selectedTheme + "\n"
                + "Selected Layout: " + selectedLayout);
    }
}
