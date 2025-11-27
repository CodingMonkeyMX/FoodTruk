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

    private void clearOrder() {
        receiptInfo.setText("");
        total.setText("Total: $0.00");
        orderList.clear();
        totalAmount = 0.0;
    }



    private void Checkout() {
        new Checkout(receiptInfo.getText(), totalAmount);
            dispose();
    }

    public void addToOrder(String itemName, double price, int quantity) {
        // Check if item already exists in order
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
            // Initialize order list
            orderList = new ArrayList<>();
            totalAmount = 0.0;
            // initializing GUI's
            setBounds(0, 0, 900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);

        JLabel title = new JLabel("Jedidiah's Menu");
            title.setBounds(360, 0, 250, 40);
            this.add(title);
            title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel menuPanel = new JPanel();
            menuPanel.setBounds(50,70,400,400);
            menuPanel.setLayout(new GridLayout(4,1));
            menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
            this.add(menuPanel);

        JPanel receipt = new JPanel();
            receipt.setBounds(460,70,400,400);
            receipt.setLayout(null);
            receipt.setBorder(BorderFactory.createTitledBorder("Order"));
            this.add(receipt);


        JPanel buttonBar = new JPanel();
            buttonBar.setBounds(50,500,810,52);
            buttonBar.setLayout(new FlowLayout());
            this.add(buttonBar);

            receiptInfo = new JTextArea("");
            receiptInfo.setBounds(5, 17, 390, 350);
            receiptInfo.setEditable(false);
            receipt.add(receiptInfo);

            total = new JLabel("Total: $0.00");
            total.setBounds(5, 360, 250, 40);
            receipt.add(total);
            total.setFont(new Font("Arial", Font.BOLD, 24));

        JButton checkOut = new JButton("CHECKOUT");
        JButton clearOrder = new JButton("CLEAR ORDER");
        JButton exit = new JButton("EXIT");

            checkOut.setFont(new Font("Arial", Font.BOLD, 14));
            clearOrder.setFont(new Font("Arial", Font.BOLD, 14));
            exit.setFont(new Font("Arial", Font.BOLD, 14));

            checkOut.setBackground(new Color(76, 175, 80));
            checkOut.setForeground(Color.WHITE);
            clearOrder.setBackground(new Color(255, 152, 0));
            clearOrder.setForeground(Color.WHITE);
            exit.setBackground(new Color(244, 67, 54));
            exit.setForeground(Color.WHITE);

           /* checkOut.addActionListener(e -> checkout()); */
            clearOrder.addActionListener(_ -> clearOrder());
            exit.addActionListener(_ -> System.exit(0));
            checkOut.addActionListener(_ -> Checkout());

            buttonBar.add(checkOut);
            buttonBar.add(clearOrder);
            buttonBar.add(exit);

            // menu panel buttons
        JButton entrees = new JButton("Entrees");
        JButton mains = new JButton(
                "<html>Mains <font color='yellow'>(</font>"
                        + "<font color='red'>HOT DEAL!</font>"
                        + "<font color='yellow'>)</font></html>"
        );

        JButton desserts = new JButton("Desserts");
        JButton drinks = new JButton("Drinks");

            entrees.setBackground(Color.white);
            mains.setBackground(Color.white);
            desserts.setBackground(Color.white);
            drinks.setBackground(Color.WHITE);

            entrees.setBorder(BorderFactory.createRaisedBevelBorder());
            mains.setBorder(BorderFactory.createRaisedBevelBorder());
            desserts.setBorder(BorderFactory.createRaisedBevelBorder());
            drinks.setBorder(BorderFactory.createRaisedBevelBorder());

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
}
