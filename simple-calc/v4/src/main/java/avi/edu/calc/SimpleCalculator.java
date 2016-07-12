package avi.edu.calc;

import avi.edu.calc.operation.Operation;
import avi.edu.calc.parser.InputParser;
import lombok.Value;

@Value
public class SimpleCalculator {
    private InputParser parser;

    public double operate(String input) {
        Operation operation = parser.parse(input);
        return operation.getOperator().operate(operation.getOperands());
    }
}
