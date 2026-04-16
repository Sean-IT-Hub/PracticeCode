package Calculator;

import javax.swing.*;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("UI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        add(new CalculatorPanel());
        setVisible(true);
    }
}
