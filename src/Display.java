import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame implements ActionListener {
    private JLabel order;
    private JLabel title;
    private JButton entreeButton;

    public Display() {
        // initializing GUI's
        setBounds(0, 0, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        title = new JLabel("FoodTruck App");
        title.setBounds(212, 0, 475, 40);
        this.add(title);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        entreeButton = new JButton("Entrees");
        entreeButton.setBounds(50, 100, 150, 40);
        entreeButton.addActionListener(this); // 'this' refers to Display class
        this.add(entreeButton);

        setVisible(true);
    }

    // REQUIRED: This method must be implemented when using ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == entreeButton) {
            entreeButtonClicked();
        }
    }

    public void entreeButtonClicked() {
        // Your code here when button is clicked
        System.out.println("Entree button clicked!");
    }
}