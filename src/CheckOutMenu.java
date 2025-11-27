import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CheckOutMenu extends JFrame {
    private JButton selectedPaymentButton = null;
    private final Color selectedColor = new Color(46, 204, 113);
    private final Color defaultColor = new Color(236, 240, 241);
    private final Color hoverColor = new Color(189, 195, 199);

    public CheckOutMenu() {
        setBounds(100, 100, 450, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Payment Methods");
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 247, 250));

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel headerLabel = new JLabel("Select Payment Method");
        headerLabel.setFont(new Font("Segue UI", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Credit Card Section
        JPanel cardSection = createPaymentSection("Credit/Debit Card", new String[]{"Visa", "Mastercard", "American Express"});
        mainPanel.add(cardSection);
        mainPanel.add(Box.createVerticalStrut(20));

        // PayPal Section
        JPanel payPalSection = createPaymentSection("PayPal", new String[]{"PayPal"});
        mainPanel.add(payPalSection);
        mainPanel.add(Box.createVerticalStrut(20));

        // Digital Wallets Section
        JPanel digitalSection = createPaymentSection("Digital Wallets", new String[]{"Apple Pay", "Google Pay"});
        mainPanel.add(digitalSection);
        mainPanel.add(Box.createVerticalStrut(20));

        // Cash Option
        JPanel cashSection = createPaymentSection("Cash", new String[]{"Pay with Cash"});
        mainPanel.add(cashSection);

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);

        // Bottom Panel with Proceed Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setBorder(new EmptyBorder(15, 30, 15, 30));
        bottomPanel.setLayout(new BorderLayout());

        JButton proceedButton = getJButton();

        bottomPanel.add(proceedButton, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton getJButton() {
        JButton proceedButton = new JButton("Proceed to Payment");
        proceedButton.setFont(new Font("Segue UI", Font.BOLD, 16));
        proceedButton.setBackground(new Color(46, 204, 113));
        proceedButton.setForeground(Color.WHITE);
        proceedButton.setFocusPainted(false);
        proceedButton.setBorderPainted(false);
        proceedButton.setPreferredSize(new Dimension(200, 50));
        proceedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        proceedButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                proceedButton.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                proceedButton.setBackground(new Color(46, 204, 113));
            }
        });

        proceedButton.addActionListener(_ -> {
            if (selectedPaymentButton != null) {
                String method = selectedPaymentButton.getText();
                JOptionPane.showMessageDialog(this,
                        "Processing payment with: " + method + "\n\nThank you for your order!",
                        "Payment Confirmation",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a payment method first.",
                        "No Payment Method Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        return proceedButton;
    }

    private JPanel createPaymentSection(String title, String[] options) {
        JPanel section = new JPanel();
        section.setLayout(new BorderLayout(10, 10));
        section.setBackground(Color.WHITE);
        section.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        // Title Label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segue UI", Font.BOLD, 16));
        titleLabel.setForeground(new Color(52, 73, 94));
        section.add(titleLabel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, options.length, 10, 0));
        optionsPanel.setBackground(Color.WHITE);

        for (String option : options) {
            JButton button = createPaymentButton(option);
            optionsPanel.add(button);
        }

        section.add(optionsPanel, BorderLayout.CENTER);
        return section;
    }

    private JButton createPaymentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segue UI", Font.PLAIN, 14));
        button.setBackground(defaultColor);
        button.setForeground(new Color(52, 73, 94));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 60));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new CompoundBorder(
                new LineBorder(new Color(189, 195, 199), 2, true),
                new EmptyBorder(5, 10, 5, 10)
        ));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button != selectedPaymentButton) {
                    button.setBackground(hoverColor);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button != selectedPaymentButton) {
                    button.setBackground(defaultColor);
                }
            }
        });

        button.addActionListener(_ -> {
            // Deselect previous button
            if (selectedPaymentButton != null) {
                selectedPaymentButton.setBackground(defaultColor);
                selectedPaymentButton.setBorder(new CompoundBorder(
                        new LineBorder(new Color(189, 195, 199), 2, true),
                        new EmptyBorder(5, 10, 5, 10)
                ));
            }

            // Select new button
            selectedPaymentButton = button;
            button.setBackground(selectedColor);
            button.setBorder(new CompoundBorder(
                    new LineBorder(new Color(39, 174, 96), 3, true),
                    new EmptyBorder(5, 10, 5, 10)
            ));
        });

        return button;
    }
}