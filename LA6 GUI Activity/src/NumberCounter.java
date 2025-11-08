import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberCounter extends JFrame implements ActionListener{
    private JPanel contentPanel;
    private JButton decreaseButton, increaseButton;
    private JLabel countLabel;
    private int counter;

    public NumberCounter() {
        setTitle("Number Counter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        pack();

        contentPanel.setName("contentPanel");
        countLabel.setName("countLabel");
        increaseButton.setName("increaseButton");
        decreaseButton.setName("decreaseButton");

        increaseButton.addActionListener(this);
        decreaseButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == increaseButton) counter++;
        if (e.getSource() == decreaseButton) counter--;
        countLabel.setText(String.valueOf(counter));
    }
}


