package Calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorPanel extends JPanel {
    private CalculatorDisplay display;
    private CalculatorLogic logic;

    public CalculatorPanel() {
        setLayout(new BorderLayout());
        display = new CalculatorDisplay();
        logic = new CalculatorLogic(display);

        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+"
        };

        for (String text : buttons) {
            buttonPanel.add(new CalculatorButton(text, logic));
        }

        add(buttonPanel, BorderLayout.CENTER);
    }
}