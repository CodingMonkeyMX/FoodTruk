import javax.swing.*;
import java.awt.*;


public class Display extends JFrame {
    private JLabel order;
    private JLabel title;
    private JPanel menuPanel;

    public Display() {
        // initializing GUI's
            setBounds(0, 0, 900, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(null);

            title = new JLabel("FoodTruck App");
            title.setBounds(212, 0, 475, 40);
            title.setFont(new Font("Arial", Font.BOLD, 24));
            this.add(title);

            menuPanel = new JPanel();
            menuPanel.setBounds(0,20,100,100);
            menuPanel.setLayout(new GridLayout(4,1));
            menuPanel.setBackground(Color.blue);
            this.add(menuPanel);

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
