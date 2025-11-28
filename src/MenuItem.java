import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuItem extends JPanel {
    private final String name;
    private final double price;
    private final JButton orderButton;
    private final JButton nutritionButton;
    private final Display display;
    private final NutritionalInfo nutritionalInfo;

    public MenuItem(String name, double price, String imagePath, Display display, NutritionalInfo nutritionalInfo) {
        this.name = name;
        this.price = price;
        this.display = display;
        this.nutritionalInfo = nutritionalInfo;

        // Modern card-style design
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2, true), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(480, 140));

        // Image with rounded corners effect
        JLabel imageLabel;
        if (imagePath != null && !imagePath.isEmpty() && new File(imagePath).exists()) {
            ImageIcon originalIcon = new ImageIcon(imagePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel = new JLabel(scaledIcon);
        } else {
            // Stylish placeholder
            imageLabel = new JLabel("");
            imageLabel.setPreferredSize(new Dimension(110, 110));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imageLabel.setOpaque(true);
            imageLabel.setBackground(new Color(236, 240, 241));
            imageLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        }
        imageLabel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2, true), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        // Name label with better styling
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        nameLabel.setForeground(new Color(44, 62, 80));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Price with attractive color
        JLabel priceLabel = new JLabel(String.format("$%.2f", price));
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        priceLabel.setForeground(new Color(46, 204, 113));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Add spacing
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalGlue());

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(Color.WHITE);

        // Order button - primary action
        orderButton = new JButton("Order");
        orderButton.setBackground(new Color(52, 152, 219));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderButton.setFocusPainted(false);
        orderButton.setBorderPainted(false);
        orderButton.setPreferredSize(new Dimension(120, 50));
        orderButton.setMaximumSize(new Dimension(120, 50));
        orderButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Nutrition button - secondary action
        nutritionButton = new JButton("Nutrition");
        nutritionButton.setBackground(new Color(243, 156, 18));
        nutritionButton.setForeground(Color.WHITE);
        nutritionButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        nutritionButton.setFocusPainted(false);
        nutritionButton.setBorderPainted(false);
        nutritionButton.setPreferredSize(new Dimension(120, 50));
        nutritionButton.setMaximumSize(new Dimension(120, 50));
        nutritionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nutritionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effects
        orderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                orderButton.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                orderButton.setBackground(new Color(52, 152, 219));
            }
        });

        nutritionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nutritionButton.setBackground(new Color(211, 136, 16));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nutritionButton.setBackground(new Color(243, 156, 18));
            }
        });

        orderButton.addActionListener(_ -> handleOrder());
        nutritionButton.addActionListener(_ -> showNutritionalInfo());

        buttonsPanel.add(orderButton);
        buttonsPanel.add(Box.createVerticalStrut(12));
        buttonsPanel.add(nutritionButton);

        // Layout
        add(imageLabel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.EAST);

        setPreferredSize(new Dimension(480, 140));
    }

    private void handleOrder() {
        // Custom input dialog with better styling
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Enter quantity for " + name + ":"));

        JTextField quantityField = new JTextField("1");
        quantityField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(quantityField);

        int result = JOptionPane.showConfirmDialog(this, panel,"Order Quantity",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String input = quantityField.getText();
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int quantity = Integer.parseInt(input.trim());
                    if (quantity > 0) {
                        display.addToOrder(name, price, quantity);
                        JOptionPane.showMessageDialog(this, quantity + "x " + name + " added to your order!", "Added to Cart", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a positive number.", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void showNutritionalInfo() {
        if (nutritionalInfo != null) {
            JTextArea textArea = new JTextArea(nutritionalInfo.getFormattedInfo());
            textArea.setEditable(false);
            textArea.setFont(new Font("Consolas", Font.PLAIN, 13));
            textArea.setBackground(new Color(250, 250, 250));
            textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 450));
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2));

            JOptionPane.showMessageDialog(this, scrollPane, "Nutritional Facts - " + name, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nutritional information not available for this item.", "Not Available", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}