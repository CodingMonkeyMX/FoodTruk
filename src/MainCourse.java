import javax.swing.*;
import java.awt.*;

public class MainCourse extends JFrame {

    public MainCourse(Display display) {

        // Set up the frame
        setTitle("Mains Menu");
        setBounds(100, 100, 500, 800);
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

        // Create MenuItem for each main course with nutritional info
        String[] mainCourse = {"Burger", "Pizza", "Steak"};
        for (int i = 0; i < mainCourse.length; i++) {
            MenuItem item = getMenuItem(display, mainCourse, i);
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
        closeButton.addActionListener(_ -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static MenuItem getMenuItem(Display display, String[] mainCourse, int i) {
        double[] prices = {14.99, 13.99, 29.99};
        String[] imagePath = {"images/burger.jpg", "images/pizza.jpg", "images/steak.png"};
        // Nutritional information for each main course
        NutritionalInfo[] nutritionalInfos = {
                // Burger: name, calories, weight(g), protein(g), carbs(g), fat(g), sugar(g), sodium(mg), fiber(g)
                new NutritionalInfo("Burger", 540, 250, 28.0, 45.0, 26.0, 8.0, 980, 3.0),

                // Pizza (slice)
                new NutritionalInfo("Pizza", 285, 120, 12.0, 36.0, 10.0, 3.5, 640, 2.5),

                // Steak
                new NutritionalInfo("Steak", 680, 300, 62.0, 2.0, 45.0, 0.0, 650, 0.0)
        };
        return new MenuItem(mainCourse[i], prices[i], imagePath[i], display, nutritionalInfos[i]);
    }
}