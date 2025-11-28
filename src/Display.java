import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Display extends JFrame {

    private final JTextArea receiptInfo;
    private final JLabel total;
    private final ArrayList<OrderItem> orderList;
    private double totalAmount;

    private static class OrderItem {
        String name;
        double price;
        int quantity;

        OrderItem(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    // Custom panel with gradient background
    static class GradientPanel extends JPanel {
        private final Color color1;
        private final Color color2;

        public GradientPanel(Color color1, Color color2) {
            this.color1 = color1;
            this.color2 = color2;
        }
    }

    private void clearOrder() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear the entire order?",
                "Clear Order",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            receiptInfo.setText("");
            total.setText("Total: $0.00");
            orderList.clear();
            totalAmount = 0.0;
        }
    }

    private void Checkout() {
        if (orderList.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Your cart is empty! Please add items before checking out.",
                    "Empty Cart",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        new Checkout(receiptInfo.getText(), totalAmount);
        dispose();
    }

    public void addToOrder(String itemName, double price, int quantity) {
        boolean found = false;
        for (OrderItem item : orderList) {
            if (item.name.equals(itemName)) {
                item.quantity += quantity;
                found = true;
                break;
            }
        }

        if (!found) {
            orderList.add(new OrderItem(itemName, price, quantity));
        }

        updateReceipt();
    }

    private void updateReceipt() {
        StringBuilder receipt = new StringBuilder();
        totalAmount = 0.0;

        for (OrderItem item : orderList) {
            double itemTotal = item.price * item.quantity;
            totalAmount += itemTotal;
            receipt.append(String.format("%dx %s - $%.2f\n",
                    item.quantity, item.name, itemTotal));
        }

        receiptInfo.setText(receipt.toString());
        total.setText(String.format("Total: $%.2f", totalAmount));
    }

    public Display() {
        orderList = new ArrayList<>();
        totalAmount = 0.0;

        setBounds(0, 0, 950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create gradient background panel
        GradientPanel mainPanel = new GradientPanel(new Color(240, 242, 245),new Color(209, 216, 224));
        mainPanel.setLayout(null);
        setContentPane(mainPanel);

        // Styled title with shadow effect
        JLabel title = new JLabel("Jedidiah's Menu");
        title.setBounds(340, 15, 300, 50);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(new Color(44, 62, 80));
        mainPanel.add(title);

        // Subtitle
        JLabel subtitle = new JLabel("Unfair prices? Call us, at (248)-434-5508");
        subtitle.setBounds(333, 60, 300, 25);
        subtitle.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        subtitle.setForeground(new Color(127, 140, 141)); //text colour jebidiahness
        mainPanel.add(subtitle);

        // Menu panel with fancy
        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(50, 110, 420, 430);
        menuPanel.setLayout(new GridLayout(4, 1, 0, 15));
        menuPanel.setBackground(new Color(255, 255, 255, 0));
        menuPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2, true), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        mainPanel.add(menuPanel);

        // Receipt panel with card-like design
        JPanel receiptPanel = new JPanel();
        receiptPanel.setBounds(490, 110, 420, 430);
        receiptPanel.setLayout(null);
        receiptPanel.setBackground(Color.WHITE);
        receiptPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(189, 195, 199), 2, true), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        mainPanel.add(receiptPanel);

        // Receipt title
        JLabel receiptTitle = new JLabel("Your Order");
        receiptTitle.setBounds(15, 5, 200, 30);
        receiptTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        receiptTitle.setForeground(new Color(52, 73, 94)); // elito notew - text colour
        receiptPanel.add(receiptTitle);

        // Receipt text area with scroll
        receiptInfo = new JTextArea("");
        receiptInfo.setBounds(15, 40, 380, 310);
        receiptInfo.setEditable(false);
        receiptInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        receiptInfo.setBackground(new Color(250, 250, 250));
        receiptInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1), BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(receiptInfo);
        scrollPane.setBounds(15, 40, 380, 310);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        receiptPanel.add(scrollPane);

        // Total with background highlight
        JPanel totalPanel = new JPanel();
        totalPanel.setBounds(15, 360, 380, 50);
        totalPanel.setBackground(new Color(46, 204, 113));
        totalPanel.setLayout(new BorderLayout());
        totalPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(39, 174, 96), 2, true), BorderFactory.createEmptyBorder(8, 15, 8, 15)));

        total = new JLabel("Total: $0.00");
        total.setFont(new Font("Segoe UI", Font.BOLD, 24));
        total.setForeground(Color.WHITE);
        totalPanel.add(total, BorderLayout.CENTER);
        receiptPanel.add(totalPanel);

        // Button bar
        JPanel buttonBar = new JPanel();
        buttonBar.setBounds(50, 560, 860, 60);
        buttonBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonBar.setBackground(new Color(255, 255, 255, 0));
        mainPanel.add(buttonBar);

        // Styled buttons
        JButton checkOut = createStyledButton("CHECKOUT", new Color(46, 204, 113), new Color(39, 174, 96));
        JButton clearOrder = createStyledButton("CLEAR ORDER", new Color(243, 156, 18), new Color(211, 136, 16));
        JButton exit = createStyledButton("EXIT", new Color(231, 76, 60), new Color(192, 57, 43));

        clearOrder.addActionListener(_ -> clearOrder());
        exit.addActionListener(_ -> System.exit(0));
        checkOut.addActionListener(_ -> Checkout());

        buttonBar.add(checkOut);
        buttonBar.add(clearOrder);
        buttonBar.add(exit);

        // Menu category buttons
        JButton entrees = createMenuButton("Entrees", new Color(231, 76, 60));
        JButton mains = createMenuButton("Mains (HOT DEAL!)", new Color(230, 126, 34));
        JButton desserts = createMenuButton("Desserts", new Color(155, 89, 182));
        JButton drinks = createMenuButton("Drinks", new Color(52, 152, 219));

        menuPanel.add(entrees);
        menuPanel.add(mains);
        menuPanel.add(desserts);
        menuPanel.add(drinks);

        entrees.addActionListener(_ -> new Entrees(this));
        mains.addActionListener(_ -> new MainCourse(this));
        desserts.addActionListener(_ -> new Desserts(this));
        drinks.addActionListener(_ -> new Drinks(this));

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor, Color hoverColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 45));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JButton createMenuButton(String text, Color accentColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(44, 62, 80));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(accentColor, 3, true), BorderFactory.createEmptyBorder(15, 20, 15, 20)));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(accentColor);
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(44, 62, 80));
            }
        });

        return button;
    }
}