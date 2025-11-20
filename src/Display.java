import javax.swing.*;
import java.awt.*;


public class Display extends JFrame {
    private JLabel order;
    private JLabel title;
    private JPanel menuPanel;
    private JPanel buttonBar;
    private JPanel receipt;
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
/*      // text input area
            dogDisplay = new JTextArea(giveDogDisplay(1));
            dogDisplay.setBounds(50, 50, 400, 350);
            this.add(dogDisplay);
            dogDisplay.setFont(new Font("Helvetica", Font.BOLD, 16));
            dogDisplay.setEditable(false);
*/


        // default button
/*          feed = new JButton("Feed");
            feed.setBounds(50, 500, 70, 40);
            this.add(feed);
            feed.addActionListener(this);
*/
        setVisible(true);
    }
}
