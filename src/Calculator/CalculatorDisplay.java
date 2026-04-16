package Calculator;

import javax.swing.*;

public class CalculatorDisplay extends JTextField {
    public CalculatorDisplay() {
        setEditable(false);
        setHorizontalAlignment(JTextField.RIGHT);
        setFont(getFont().deriveFont(24f));
    }

    public void setTextValue(String value) {
        setText(value);
    }

    public String getTextValue() {
        return getText();
    }
}
