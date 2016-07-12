package avi.edu.calc;

import avi.edu.calc.operation.Operation;
import lombok.Value;

import java.util.Arrays;

@Value
public class SimpleCalculator {
    private InputParser parser;

    public int operate(String input) {
        Operation operation = parser.parse(input);
        return operation.getOperator().operate(operation.getOperands());
    }
}
