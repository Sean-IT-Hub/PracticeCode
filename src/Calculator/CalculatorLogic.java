package Calculator;

public class CalculatorLogic {
    private CalculatorDisplay display;
    private String currentInput = "";
    private double firstOperand = 0;
    private String operator = "";

    public CalculatorLogic(CalculatorDisplay display) {
        this.display = display;
    }

    public void processInput(String input) {
        if ("0123456789.".contains(input)) {
            currentInput += input;
            display.setTextValue(currentInput);
        } else if ("+-*/".contains(input)) {
            firstOperand = Double.parseDouble(currentInput);
            operator = input;
            currentInput = "";
        } else if ("=".equals(input)) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;
            switch (operator) {
                case "+": result = firstOperand + secondOperand; break;
                case "-": result = firstOperand - secondOperand; break;
                case "*": result = firstOperand * secondOperand; break;
                case "/": result = firstOperand / secondOperand; break;
            }
            display.setTextValue(String.valueOf(result));
            currentInput = String.valueOf(result);
        }
    }
}