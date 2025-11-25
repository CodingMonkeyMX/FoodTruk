import javax.swing.*;
import java.awt.*;

public class MainCourse extends JFrame {
    private Display display;
    private String[] mainCourse = {"Burger", "Pizza", "Steak"};
    private double[] prices = {14.99, 13.99, 29.99};

    public MainCourse(Display display) {
        this.display = display;

        // Set up the frame
        setTitle("Mains Menu");
        setBounds(100, 100, 450, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Mains", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Menu items panel
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create MenuItem for each entree
        for (int i = 0; i < mainCourse.length; i++) {
            MenuItem item = new MenuItem(mainCourse[i], prices[i], display);
            menuItemsPanel.add(item);
            menuItemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Add scroll pane in case there are many items
        JScrollPane scrollPane = new JScrollPane(menuItemsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Back to Menu");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBackground(new Color(158, 158, 158));
        closeButton.setForeground(Color.WHITE);
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}