import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuItem extends JPanel {
    private String name;
    private double price;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JButton orderButton;
    private Display display;

    public MenuItem(String name, double price, String imagePath, Display display) {
        this.name = name;
        this.price = price;
        this.display = display;

        // Set up the panel with more appealing design
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(450, 120));

        // Image - with fallback for missing images
        JLabel imageLabel;
        if (imagePath != null && !imagePath.isEmpty() && new File(imagePath).exists()) {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel = new JLabel(scaledIcon);
        } else {
            // Create a placeholder if image is missing
            imageLabel = new JLabel("No Image");
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(240, 240, 240));
            imageLabel.setForeground(new Color(150, 150, 150));
            imageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        }
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 2));

        // Create name label - larger and bolder
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(new Color(50, 50, 50));

        // Create price label - more prominent
        priceLabel = new JLabel(String.format("$%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(46, 125, 50));

        // Info panel with better spacing
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(priceLabel);

        // Create order button - improved design
        orderButton = new JButton("Order");
        orderButton.setBackground(new Color(33, 150, 243));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("Arial", Font.BOLD, 16));
        orderButton.setFocusPainted(false);
        orderButton.setBorderPainted(false);
        orderButton.setPreferredSize(new Dimension(100, 100));
        orderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        orderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                orderButton.setBackground(new Color(25, 118, 210));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                orderButton.setBackground(new Color(33, 150, 243));
            }
        });

        orderButton.addActionListener(e -> handleOrder());

        // Layout
        add(imageLabel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(orderButton, BorderLayout.EAST);

        setPreferredSize(new Dimension(450, 120));
    }

    private void handleOrder() {
        String input = JOptionPane.showInputDialog(this,"Enter quantity for " + name + ":","Order Quantity", JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int quantity = Integer.parseInt(input.trim());
                if (quantity > 0) {
                    display.addToOrder(name, price, quantity);
                    JOptionPane.showMessageDialog(this,quantity + "x " + name + " added to order!","Success",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,"Please enter a positive number.","Invalid Quantity",JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Please enter a valid number.","Invalid Input",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public String getItemName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}