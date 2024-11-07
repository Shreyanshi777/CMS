import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MediaManagementPanel extends JPanel {
    private static final int MAX_MEDIA_SIZE_MB = 100; // Maximum media size limit in MB
    private JPanel mediaPreviewPanel; // Panel to hold media previews
    private JTextField mediaPathField;
    private JTextArea tagField; // JTextArea for tags
    private JButton addMediaBtn, updateMediaBtn, deleteMediaBtn;
    private JPanel selectedMediaItemPanel = null; // Tracks selected media item panel for updating/deleting

    public MediaManagementPanel() {
        setLayout(new BorderLayout());
      

        // Media Preview Section
        mediaPreviewPanel = new JPanel();
        mediaPreviewPanel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 columns with 10px gap
        add(new JScrollPane(mediaPreviewPanel), BorderLayout.CENTER); // Add scroll pane for media preview

        // Bottom Panel for Media Management
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout()); // FlowLayout for side-by-side components
       
        // Input fields and labels
        bottomPanel.add(new JLabel("Image Path:"));
        mediaPathField = new JTextField(20);
        bottomPanel.add(mediaPathField);

        bottomPanel.add(new JLabel("Tags:"));
        tagField = new JTextArea(2, 15); 
        bottomPanel.add(new JScrollPane(tagField));

        // Add media and action buttons
        addMediaBtn = new JButton("Add Media");
        updateMediaBtn = new JButton("Update Media");
        deleteMediaBtn = new JButton("Delete Media");
        

        bottomPanel.add(addMediaBtn);
        bottomPanel.add(updateMediaBtn);
        bottomPanel.add(deleteMediaBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button action listeners
        addMediaBtn.addActionListener(new AddMediaAction());
        updateMediaBtn.addActionListener(new UpdateMediaAction());
        deleteMediaBtn.addActionListener(new DeleteMediaAction());
    }

  

    private class AddMediaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mediaFilePath = mediaPathField.getText().trim();
            String tags = tagField.getText().trim();

            if (mediaFilePath.isEmpty()) {
                showErrorDialog("Please enter a valid file path.");
                return;
            }

            File mediaFile = new File(mediaFilePath);
            if (!mediaFile.exists()) {
                showErrorDialog("File does not exist. Please check the path.");
                return;
            }

            long fileSizeInMB = mediaFile.length() / (1024 * 1024);
            if (fileSizeInMB > MAX_MEDIA_SIZE_MB) {
                showErrorDialog("File size exceeds 100 MB limit.");
                return;
            }

            // Load and display image as a square thumbnail
            ImageIcon originalIcon = new ImageIcon(mediaFilePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon squareIcon = new ImageIcon(scaledImage);

            // Create media item panel with image, tags, and selection ability
            JPanel mediaItemPanel = new JPanel();
            mediaItemPanel.setLayout(new BoxLayout(mediaItemPanel, BoxLayout.Y_AXIS));
            mediaItemPanel.setPreferredSize(new Dimension(100, 120)); // Square thumbnail with space for tags
            mediaItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel imageLabel = new JLabel(squareIcon);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mediaItemPanel.add(imageLabel);

            JLabel tagsLabel = new JLabel("<html>" + tags.replace("\n", "<br/>") + "</html>");
            tagsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            tagsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mediaItemPanel.add(tagsLabel);

            // Add click listener to select the media item
            mediaItemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    selectMediaItem(mediaItemPanel, mediaFilePath, tags);
                }
            });

            mediaPreviewPanel.add(mediaItemPanel);
            mediaPreviewPanel.revalidate();

            // Clear input fields after adding
            mediaPathField.setText("");
            tagField.setText("");
        }
    }

    // Display error dialog with customized colors
    private void showErrorDialog(String message) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.getContentPane().setBackground(new Color(66, 165, 245)); 
        optionPane.setBackground(new Color(66, 165, 245)); 
        optionPane.setForeground(Color.WHITE); // Set text color to white
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    // Selects a media item and loads its details into the input fields for updating
    private void selectMediaItem(JPanel mediaItemPanel, String filePath, String tags) {
        if (selectedMediaItemPanel != null) {
            selectedMediaItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); 
        }
        selectedMediaItemPanel = mediaItemPanel;
        selectedMediaItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE)); // Highlight selected

        mediaPathField.setText(filePath);
        tagField.setText(tags);
    }

    // Update action for the selected media item
    private class UpdateMediaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedMediaItemPanel == null) {
                showErrorDialog("Please select a media item to update.");
                return;
            }

            String newFilePath = mediaPathField.getText().trim();
            String newTags = tagField.getText().trim();

            File newMediaFile = new File(newFilePath);
            if (!newMediaFile.exists()) {
                showErrorDialog("File does not exist. Please check the path.");
                return;
            }

            ImageIcon newIcon = new ImageIcon(newFilePath);
            Image scaledImage = newIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon squareIcon = new ImageIcon(scaledImage);

            // Update image and tags on the selected media panel
            JLabel imageLabel = (JLabel) selectedMediaItemPanel.getComponent(0);
            JLabel tagsLabel = (JLabel) selectedMediaItemPanel.getComponent(1);

            imageLabel.setIcon(squareIcon);
            tagsLabel.setText("<html>" + newTags.replace("\n", "<br/>") + "</html>");

            // Clear input fields after updating
            mediaPathField.setText("");
            tagField.setText("");
            selectedMediaItemPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            selectedMediaItemPanel = null;
        }
    }

    // Delete action for the selected media item
    private class DeleteMediaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedMediaItemPanel == null) {
                showErrorDialog("Please select a media item to delete.");
                return;
            }

            mediaPreviewPanel.remove(selectedMediaItemPanel);
            mediaPreviewPanel.revalidate();
            mediaPreviewPanel.repaint();

            // Clear input fields after deleting
            mediaPathField.setText("");
            tagField.setText("");
            selectedMediaItemPanel = null;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Media Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new MediaManagementPanel());
        frame.setVisible(true);
    }
}
