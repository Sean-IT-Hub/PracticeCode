package Calculator;

import javax.swing.*;
import java.awt.event.*;

public class CalculatorButton extends JButton {
    public CalculatorButton(String text, CalculatorLogic logic) {
        super(text);
        setFont(getFont().deriveFont(18f));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logic.processInput(text);
            }
        });
    }
}
