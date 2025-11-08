import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class SimpleCalculator extends JFrame implements ActionListener{
    private JPanel contentPanel;
    private JTextField tfNumber1, tfNumber2;
    private JComboBox<Operation> cbOperations;
    private JLabel lblResult;
    private JButton btnCompute;
    private double result;

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public SimpleCalculator(){
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        pack();

        contentPanel.setName("contentPanel");
        tfNumber1.setName("tfNumber1");
        tfNumber2.setName("tfNumber2");
        cbOperations.setName("cbOperations");
        lblResult.setName("lblResult");
        btnCompute.setName("btnCompute");

        populateOperationComboBox();
        cbOperations.setSelectedItem("+");
        btnCompute.addActionListener(this);

        setVisible(true);
    }

    private void populateOperationComboBox() {
        DefaultComboBoxModel<Operation> comboBoxModel = (DefaultComboBoxModel<Operation>) cbOperations.getModel();

        for (Operation genre : Operation.values()) {
            comboBoxModel.addElement(genre);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            double num1 = Double.parseDouble(tfNumber1.getText());
            double num2 = Double.parseDouble(tfNumber2.getText());
            if (e.getSource() == btnCompute) {
                Operation operation = (Operation) cbOperations.getSelectedItem();
                assert operation != null;
                switch (operation.getSymbol()) {
                    case "+" ->
                            result = num1 + num2;
                    case "-" ->
                            result = num1 - num2;
                    case "*" ->
                            result = num1 * num2;
                    case "/" -> {
                        if (num2 == 0) {
                            lblResult.setText("Error");
                            JOptionPane.showMessageDialog(this, "Error: Division by zero.", "Calculation Error", ERROR_MESSAGE);
                            return;
                        }
                        result = num1 / num2;
                    }
                }
                lblResult.setText(DECIMAL_FORMAT.format(result));
            }
        }
        catch(NumberFormatException nfe){
            lblResult.setText("Invalid");
            JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers in both fields.", "Input Error", ERROR_MESSAGE);
        }
    }

    public enum Operation {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/");

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        @Override
        public String toString(){
            return symbol;
        }
    }
}
