import javax.swing.*;
import java.awt.*;

public class Drinks extends JFrame {

    public Drinks(Display display) {

        // Set up the frame
        setTitle("Drinks Menu");
        setBounds(100, 100, 500, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Title label
        JLabel title = new JLabel("Drinks", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Menu items panel
        JPanel menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        menuItemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create MenuItem for each entree
        String[] drinks = new String[]{"Coca cola", "Orange Juice", "Fiji Water Bottle"};
        for (int i = 0; i < drinks.length; i++) {
            MenuItem item = getMenuItem(display, drinks, i);
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

    private static MenuItem getMenuItem(Display display, String[] drinks, int i) {
        double[] prices = {3.99, 3.99, 10.99};
        String[] imagePath = {"images/Coke1.png", "images/Oj.png", "images/fiji.jpg"};
        // Nutritional information for each drink
        NutritionalInfo[] nutritionalInfos = {
                // Coca-cola (12 oz can): name, calories, weight(g), protein(g), carbs(g), fat(g), sugar(g), sodium(mg), fiber(g)
                new NutritionalInfo("Coca Cola", 140, 355, 0.0, 39.0, 0.0, 39.0, 45, 0.0),

                // Orange Juice (12 oz)
                new NutritionalInfo("Orange Juice", 168, 355, 2.5, 40.0, 0.5, 33.0, 2, 0.7),

                // Water Bottle (16.9 oz / 500ml)
                new NutritionalInfo("Water Bottle", 0, 500, 0.0, 0.0, 0.0, 0.0, 0, 0.0)
        };
        return new MenuItem(drinks[i], prices[i], imagePath[i], display, nutritionalInfos[i]);
    }
}