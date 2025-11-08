import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class FoodOrderingSystem extends JFrame implements ActionListener{
    private JCheckBox cPizza, cBurger, cFries, cSoftDrinks, cTea, cSundae;
    private final Map<JCheckBox, Double> foodCheckBoxes = new LinkedHashMap<>();
    private JRadioButton rbNone, rb5, rb10, rb15;
    private JButton btnOrder;
    private JPanel contentPanel;
    private JLabel foodsLabel, discountsLabel, Php100, Php80, Php65, Php55, Php50, Php40;
    private double amount, discount_rate;

    public FoodOrderingSystem(){
        setTitle("Food Ordering System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        pack();

        contentPanel.setName("contentPanel");
        foodsLabel.setName("foodsLabel");
        discountsLabel.setName("discountsLabel");
        cPizza.setName("cPizza");
        cBurger.setName("cBurger");
        cFries.setName("cFries");
        cSoftDrinks.setName("cSoftDrinks");
        cTea.setName("cTea");
        cSundae.setName("cSundae");
        rbNone.setName("rbNone");
        rb5.setName("rb5");
        rb10.setName("rb10");
        rb15.setName("rb15");
        btnOrder.setName("btnOrder");

        rbNone.setSelected(true);
        addFoodCheckBoxes();
        btnOrder.addActionListener(this);

        setVisible(true);
    }

    private void addFoodCheckBoxes(){
        foodCheckBoxes.put(cPizza, 100.0);
        foodCheckBoxes.put(cBurger, 80.0);
        foodCheckBoxes.put(cFries, 65.0);
        foodCheckBoxes.put(cSoftDrinks, 55.0);
        foodCheckBoxes.put(cTea, 50.0);
        foodCheckBoxes.put(cSundae, 40.0);
    }

    private void resetData(){
        amount = 0.0;
        discount_rate = 0.0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnOrder){
            for(Map.Entry<JCheckBox, Double> food: foodCheckBoxes.entrySet()){
                if(food.getKey().isSelected()) amount += food.getValue();
            }

            if(rb5.isSelected()){
                discount_rate = 0.05;
            }else if(rb10.isSelected()){
                discount_rate = 0.10;
            }else if(rb15.isSelected()) {
                discount_rate = 0.15;
            }

            double total_amount = amount * (1 - discount_rate);

            DecimalFormat df = new DecimalFormat("0.00");
            String message = "The total price is Php " + df.format(total_amount);

            JOptionPane.showMessageDialog(this, message, "Order Total", INFORMATION_MESSAGE);

            resetData();
        }
    }
}
