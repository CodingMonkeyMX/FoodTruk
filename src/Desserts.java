import javax.swing.*;
import java.awt.*;

public class Desserts extends JFrame {
    private Display display;
    private String[] desserts = {"Ice Cream", "Cheesecake", "Tres Leches"};
    private double[] prices = {7.99, 9.99, 6.99};
    private String[] imagePath = {"images/iceCream.jpg","images/cheeseCake.jpg","images/tresLeches.jpg"};

    public Desserts(Display display) {
        this.display = display;

        // Set up the frame
        setTitle("Desserts Menu");
        setBounds(100, 100, 450, 800);
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

        // Create MenuItem for each entree
        for (int i = 0; i < desserts.length; i++) {
            MenuItem item = new MenuItem(desserts[i], prices[i],imagePath[i], display);
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