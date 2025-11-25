import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Checkout extends JFrame {

    public Checkout(ArrayList<?> orderList, String receiptText, double totalAmount) {

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
        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        receiptArea.setText(receiptText + "\n\nTotal: $" + String.format("%.2f", totalAmount));

        JScrollPane scroll = new JScrollPane(receiptArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Your Receipt"));
        add(scroll, BorderLayout.CENTER);

        // Buttons panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        JButton confirm = new JButton("Confirm Purchase");
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
        cancel.addActionListener(e -> dispose());

        confirm.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Payment Completed!\nThank you for your purchase.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        save.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("receipt.txt")) {
                writer.write(receiptArea.getText());
                JOptionPane.showMessageDialog(this,
                        "Receipt saved as receipt.txt",
                        "Saved",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error saving file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
