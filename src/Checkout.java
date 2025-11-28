import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class Checkout extends JFrame {
    private final double totalWithTax;

    public Checkout(String receiptText, double totalAmount) {
        double tax = totalAmount * 0.13;
        this.totalWithTax = totalAmount + tax;

        setTitle("Checkout");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Title
        JLabel title = new JLabel("Order Checkout", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Receipt area
        JTextArea receiptArea = getJTextArea(receiptText, totalAmount, tax);

        JScrollPane scroll = new JScrollPane(receiptArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Your Receipt"));
        add(scroll, BorderLayout.CENTER);

        // Buttons panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton confirm = new JButton("Payment Options");
        JButton save = new JButton("Save Receipt");
        JButton cancel = new JButton("Cancel");

        confirm.setBackground(new Color(76, 175, 80));
        confirm.setForeground(Color.WHITE);

        save.setBackground(new Color(33, 150, 243));
        save.setForeground(Color.WHITE);

        cancel.setBackground(new Color(244, 67, 54));
        cancel.setForeground(Color.WHITE);

        bottomPanel.add(confirm);
        bottomPanel.add(save);
        bottomPanel.add(cancel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        cancel.addActionListener(_ -> dispose());

        confirm.addActionListener(_ -> {
            new CheckOutMenu(totalWithTax);
            dispose(); // Close checkout window when opening payment options
        });

        save.addActionListener(_ -> {
            try (FileWriter writer = new FileWriter("receipt.txt")) {
                writer.write(receiptArea.getText());
                JOptionPane.showMessageDialog(this,"Receipt saved as receipt.txt","Saved",JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,"Error saving file: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private JTextArea getJTextArea(String receiptText, double totalAmount, double tax) {
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        receiptArea.setText(receiptText +
                "------------------------------------------------\n\n" +
                String.format("Subtotal: $%.2f\n", totalAmount) +
                String.format("Tax (13%%): $%.2f\n", tax) +
                "------------------------------------------------\n" +
                String.format("Total: $%.2f", totalWithTax));
        return receiptArea;
    }
}