import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Display extends JFrame {
    private final JLabel title;
    private JPanel menuPanel;
    private JPanel buttonBar;
    private JPanel receipt;
    private JButton checkOut;
    private JButton clearOrder;
    private JButton exit;
    private JButton entrees;
    private JButton mains;
    private JButton desserts;
    private JButton drinks;

    private JTextArea receiptInfo;
    private JLabel total;
    private ArrayList<OrderItem> orderList;
    private double totalAmount;

    private class OrderItem {
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

            title = new JLabel("FoodTruck App");
            title.setBounds(360, 0, 250, 40);
            this.add(title);
            title.setFont(new Font("Arial", Font.BOLD, 24));

            menuPanel = new JPanel();
            menuPanel.setBounds(50,70,400,400);
            menuPanel.setLayout(new GridLayout(4,1));
            menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));
            this.add(menuPanel);

            receipt = new JPanel();
            receipt.setBounds(460,70,400,400);
            receipt.setLayout(null);
            receipt.setBorder(BorderFactory.createTitledBorder("Order"));
            this.add(receipt);


            buttonBar = new JPanel();
            buttonBar.setBounds(50,500,810,52);
            buttonBar.setLayout(new FlowLayout());
            this.add(buttonBar);

            receiptInfo = new JTextArea("");
            receiptInfo.setBounds(5, 17, 390, 350);
            receiptInfo.setEditable(false);
            receipt.add(receiptInfo);

            total = new JLabel("Total: 1.12$");
            total.setBounds(5, 360, 250, 40);
            receipt.add(total);
            total.setFont(new Font("Arial", Font.BOLD, 24));

            checkOut = new JButton("CHECKOUT");
            clearOrder = new JButton("CLEAR ORDER");
            exit = new JButton("EXIT");

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
            clearOrder.addActionListener(e -> clearOrder());
            exit.addActionListener(e -> System.exit(0));

            buttonBar.add(checkOut);
            buttonBar.add(clearOrder);
            buttonBar.add(exit);

            // menu panel buttons
            entrees = new JButton("Entrees");
            mains = new JButton("Mains");
            desserts = new JButton("Desserts");
            drinks = new JButton("Drinks");

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

            entrees.addActionListener(e -> new Entrees(this));

        // default button
/*          feed = new JButton("Feed");
            feed.setBounds(50, 500, 70, 40);
            this.add(feed);
            feed.addActionListener(this);
*/
        setVisible(true);
    }
}
