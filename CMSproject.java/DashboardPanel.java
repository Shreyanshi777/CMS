import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private JLabel recentActivityLabel;
    private JLabel trafficInsightsLabel;
    private JLabel contentInsightsLabel;

    public DashboardPanel() {
        setLayout(new BorderLayout());

        // Section title
        JLabel titleLabel = new JLabel("Dashboard Overview", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Panel for different sections
        JPanel dashboardContent = new JPanel();
        dashboardContent.setLayout(new GridLayout(3, 1, 10, 10));

        // Recent Activities Section
        recentActivityLabel = new JLabel("<html><h3>Recent Activities</h3>"
            + "<ul>"
            + "<li>New post added: 'Understanding Java Basics'</li>"
            + "<li>Comment on 'Spring Boot Guide'</li>"
            + "<li>User 'JohnDoe' signed up</li>"
            + "</ul></html>");
        JPanel recentActivityPanel = new JPanel(new BorderLayout());
        recentActivityPanel.setBorder(BorderFactory.createTitledBorder("Recent Activities"));
        recentActivityPanel.add(recentActivityLabel, BorderLayout.CENTER);

        // Traffic Insights Section
        trafficInsightsLabel = new JLabel("<html><h3>Traffic Insights</h3>"
            + "<p>Daily Visits: 1500</p>"
            + "<p>Most popular post: 'Intro to Data Structures'</p>"
            + "<p>Top referrer: Google</p>"
            + "</html>");
        JPanel trafficInsightsPanel = new JPanel(new BorderLayout());
        trafficInsightsPanel.setBorder(BorderFactory.createTitledBorder("Traffic Insights"));
        trafficInsightsPanel.add(trafficInsightsLabel, BorderLayout.CENTER);

        // Content Insights Section
        contentInsightsLabel = new JLabel("<html><h3>Content Engagement</h3>"
            + "<p>'Spring Boot Guide': 200 views, 15 shares, 5 comments</p>"
            + "<p>'Intro to Java': 120 views, 10 shares, 3 comments</p>"
            + "<p>'Microservices Architecture': 180 views, 20 shares, 7 comments</p>"
            + "</html>");
        JPanel contentInsightsPanel = new JPanel(new BorderLayout());
        contentInsightsPanel.setBorder(BorderFactory.createTitledBorder("Content Insights"));
        contentInsightsPanel.add(contentInsightsLabel, BorderLayout.CENTER);

        // Add sections to dashboard content panel
        dashboardContent.add(recentActivityPanel);
        dashboardContent.add(trafficInsightsPanel);
        dashboardContent.add(contentInsightsPanel);

        // Add content panel to main dashboard panel
        add(dashboardContent, BorderLayout.CENTER);

   
    }
}
