import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PostOptionsPanel extends JPanel {
    private JTextArea contentTextArea;
    private JTextField titleTextField;
    private JButton saveBtn, updateBtn, deleteBtn;
    private JList<String> postList;
    private DefaultListModel<String> listModel;
    private ArrayList<String> postTitles;

    public PostOptionsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title Field for the Post
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Post Title: ");
        titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
        titlePanel.add(titleLabel, BorderLayout.WEST);

        titleTextField = new JTextField();
        titleTextField.setFont(new Font("Sans Serif", Font.PLAIN, 18)); 
        titlePanel.add(titleTextField, BorderLayout.CENTER);

        // Content Text Area for the Post
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        JLabel contentLabel = new JLabel("Content: ");
        contentLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
        contentPanel.add(contentLabel, BorderLayout.NORTH);

        contentTextArea = new JTextArea(10, 40); // Set size of text area
        contentTextArea.setFont(new Font("Sans Serif", Font.PLAIN, 18)); 
        contentTextArea.setLineWrap(true); // Automatically wrap lines
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

      
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        saveBtn = new JButton("Save Post");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 18)); 
        buttonPanel.add(saveBtn);

        updateBtn = new JButton("Update Post");
        updateBtn.setFont(new Font("Sans Serif", Font.BOLD, 18));
        buttonPanel.add(updateBtn);

        deleteBtn = new JButton("Delete Post");
        deleteBtn.setFont(new Font("Sans Serif", Font.BOLD, 18));
        buttonPanel.add(deleteBtn);

        // List of saved posts (to be displayed on the right of the panel)
        postTitles = new ArrayList<>();
        listModel = new DefaultListModel<>();
        postList = new JList<>(listModel);
        postList.setFont(new Font("Sans Serif", Font.PLAIN, 18)); // Set font size for list
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Single selection mode
        JScrollPane listScrollPane = new JScrollPane(postList);
        listScrollPane.setPreferredSize(new Dimension(250, 300)); // Make the list bigger
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Posts list"));
        
        // Layout the components
        JPanel contentAndListPanel = new JPanel(new BorderLayout());
        contentAndListPanel.add(contentPanel, BorderLayout.CENTER);
        contentAndListPanel.add(listScrollPane, BorderLayout.EAST); // Display saved posts on the right

        add(titlePanel, BorderLayout.NORTH);
        add(contentAndListPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom

        // Button actions
        saveBtn.addActionListener(e -> savePost());
        updateBtn.addActionListener(e -> updatePost());
        deleteBtn.addActionListener(e -> deletePost());
        
        // List selection to load post details for updating
        postList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedPost = postList.getSelectedValue();
                    if (selectedPost != null) {
                        titleTextField.setText(selectedPost); // Set title
                        // Load content (in real implementation, you might load from file/database)
                        contentTextArea.setText("Content of " + selectedPost); // Placeholder content for now
                    }
                }
            }
        });
    }

    // Save post
    private void savePost() {
        String title = titleTextField.getText();
        String content = contentTextArea.getText();
        
        if (!title.isEmpty() && !content.isEmpty()) {
            int response = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to save this post?", 
                "Save Post", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                postTitles.add(title);  // Save title to postTitles list
                listModel.addElement(title); // Add title to JList model
                titleTextField.setText(""); // Clear title field
                contentTextArea.setText(""); // Clear content area
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please provide both title ");
        }
    }

    // Update post (example logic, should be expanded for actual file/database handling)
    private void updatePost() {
        String selectedPost = postList.getSelectedValue();
        if (selectedPost != null) {
            String newTitle = titleTextField.getText();
            String newContent = contentTextArea.getText();

            if (!newTitle.isEmpty() && !newContent.isEmpty()) {
                // Replace title in postTitles list
                int index = postTitles.indexOf(selectedPost);
                postTitles.set(index, newTitle);

                // Update list display
                listModel.setElementAt(newTitle, index);
                titleTextField.setText(""); // Clear title field
                contentTextArea.setText(""); // Clear content area
            } else {
                JOptionPane.showMessageDialog(this, "Please provide both title and content.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a post to update.");
        }
    }

    // Delete post
    private void deletePost() {
        String selectedPost = postList.getSelectedValue();
        if (selectedPost != null) {
            int response = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete the selected post?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                postTitles.remove(selectedPost);
                listModel.removeElement(selectedPost); // Remove from list
                titleTextField.setText(""); // Clear title field
                contentTextArea.setText(""); // Clear content area
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a post to delete.");
        }
    }
}
