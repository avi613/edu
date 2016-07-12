package avi.edu.calc;

import avi.edu.calc.operation.Operation;
import avi.edu.calc.operator.Addition;
import avi.edu.calc.operator.Multiplication;
import avi.edu.calc.operator.Operator;
import avi.edu.calc.operator.Subtraction;

import java.util.Arrays;

public class InputParser {
    private final String regex = "\\+|\\*|-";

    public Operator parseOperator(String input) {
        if (input.contains("+")) {
            return new Addition();
        }
        if (input.contains("*")) {
            return new Multiplication();
        }
        if (input.contains("-")) {
            return new Subtraction();
        }

        throw new IllegalArgumentException("no operator found");
    }

    public boolean isNumberOfOperandsValid(String input) {
        return input.trim().split(regex).length <= 2;
    }

    public int[] parseOperands(String input) {
        if (isNumberOfOperandsValid(input)) {
            return Arrays.stream(input.split(regex)).map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
        throw new IllegalArgumentException("number of operands must not exceed 2");
    }

    public Operation parse(String input) {
        return new Operation(parseOperands(input), parseOperator(input));
    }
}
