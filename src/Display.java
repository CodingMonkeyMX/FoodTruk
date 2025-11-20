import javax.swing.*;
import java.awt.*;


public class Display extends JFrame {
    private JLabel order;
    private JLabel title;
    private JPanel menuPanel;
    private JPanel buttonBar;
    private JPanel receipt;
    private JButton checkOut;
    private JButton clearOrder;
    private JButton exit;

    private JTextArea receiptInfo;
    private JLabel total;
    public Display() {
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
            menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(menuPanel);

            receipt = new JPanel();
            receipt.setBounds(460,70,400,400);
            receipt.setLayout(null);
            receipt.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(receipt);


            buttonBar = new JPanel();
            buttonBar.setBounds(50,475,810,52);
            buttonBar.setLayout(new FlowLayout());
            buttonBar.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(buttonBar);

            receiptInfo = new JTextArea("Asbestos: $0.99");
            receiptInfo.setBounds(5, 5, 390, 350);
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

           /* checkOut.addActionListener(e -> checkout());
            clearOrder.addActionListener(e -> clearOrder());*/
            exit.addActionListener(e -> System.exit(0));

            buttonBar.add(checkOut);
            buttonBar.add(clearOrder);
            buttonBar.add(exit);

        // default button
/*          feed = new JButton("Feed");
            feed.setBounds(50, 500, 70, 40);
            this.add(feed);
            feed.addActionListener(this);
*/
        setVisible(true);
    }
}
