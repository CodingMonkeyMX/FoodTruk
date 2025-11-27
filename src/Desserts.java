import javax.swing.*;
import java.awt.*;

public class Desserts extends JFrame {

    public Desserts(Display display) {

        // Set up the frame
        setTitle("Desserts Menu");
        setBounds(100, 100, 500, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title label
        JLabel title = new JLabel("Desserts", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Menu items panel
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create MenuItem for each dessert with nutritional info
        String[] desserts = {"Ice Cream", "Cheesecake", "Tres Leches"};
        for (int i = 0; i < desserts.length; i++) {
            MenuItem item = getMenuItem(display, desserts, i);
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

    private static MenuItem getMenuItem(Display display, String[] desserts, int i) {
        double[] prices = {7.99, 9.99, 6.99};
        String[] imagePath = {"images/iceCream.jpg", "images/cheeseCake.jpg", "images/tresLeches.jpg"};
        // Nutritional information for each dessert
        // Ice Cream: name, calories, weight(g), protein(g), carbs(g), fat(g), sugar(g), sodium(mg), fiber(g)
        // Cheesecake
        // Tres Leches Cake
        NutritionalInfo[] nutritionalInfos = {
                // Ice Cream: name, calories, weight(g), protein(g), carbs(g), fat(g), sugar(g), sodium(mg), fiber(g)
                new NutritionalInfo("Ice Cream", 350, 150, 5.0, 42.0, 18.0, 35.0, 120, 0.5),

                // Cheesecake
                new NutritionalInfo("Cheesecake", 450, 180, 8.0, 48.0, 25.0, 38.0, 380, 1.0),

                // Tres Leches Cake
                new NutritionalInfo("Tres Leches", 380, 200, 9.0, 55.0, 14.0, 42.0, 240, 0.8)
        };
        return new MenuItem(desserts[i], prices[i], imagePath[i], display, nutritionalInfos[i]);
    }
}