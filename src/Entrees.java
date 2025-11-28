import javax.swing.*;
import java.awt.*;

public class Entrees extends JFrame {

    public Entrees(Display display) {

        // Set up the frame
        setTitle("Entrees Menu");
        setBounds(100, 100, 500, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Entrees", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Menu items panel
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create MenuItem for each entree with nutritional info
        String[] entrees = {"Hot Dog", "Elote", "Wonton Soup"};
        for (int i = 0; i < entrees.length; i++) {
            MenuItem item = getMenuItem(display, entrees, i);
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

    private static MenuItem getMenuItem(Display display, String[] entrees, int i) {
        double[] prices = {3.99, 4.99, 7.99};
        String[] imagePath = {"images/Hotdog.png", "images/elote.jpg", "images/Wonton.jpg"};
        // Nutritional information for each item
        NutritionalInfo[] nutritionalInfos = {
                // Hot Dog: name, calories, weight(g), protein(g), carbs(g), fat(g), sugar(g), sodium(mg), fiber(g)
                new NutritionalInfo("Hot Dog", 290, 150, 10.5, 25.0, 16.0, 3.5, 810, 1.5),

                // Elote (Mexican Street Corn)
                new NutritionalInfo("Elote", 220, 200, 6.0, 28.0, 11.0, 5.0, 450, 3.5),

                // Wonton Soup
                new NutritionalInfo("Wonton Soup", 180, 350, 12.0, 22.0, 5.0, 2.0, 920, 2.0)
        };
        return new MenuItem(entrees[i], prices[i], imagePath[i], display, nutritionalInfos[i]);
    }
}