import javax.swing.*;
import java.awt.*;

public class MenuItem extends JPanel {
    private String name;
    private double price;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JButton orderButton;
    private Display display;

    public MenuItem(String name, double price, Display display) {
        this.name = name;
        this.price = price;
        this.display = display;

        // Set up the panel
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 1),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        setBackground(Color.WHITE);

        // Create name label
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Create price label
        priceLabel = new JLabel(String.format("$%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(76, 175, 80));

        // Create order button
        orderButton = new JButton("Order");
        orderButton.setBackground(new Color(33, 150, 243));
        orderButton.setForeground(Color.WHITE);
        orderButton.setFocusPainted(false);
        orderButton.addActionListener(e -> handleOrder());

        // Layout components
        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(nameLabel);
        infoPanel.add(priceLabel);

        add(infoPanel, BorderLayout.CENTER);
        add(orderButton, BorderLayout.EAST);

        setPreferredSize(new Dimension(350, 70));
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