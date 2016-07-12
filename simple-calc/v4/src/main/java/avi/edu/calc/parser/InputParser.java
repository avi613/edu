package avi.edu.calc.parser;

import avi.edu.calc.operation.Operation;
import avi.edu.calc.operator.*;

import java.util.Arrays;

public class InputParser {
    private final String regex = "\\+|\\*|-|/";

    public Operation parse(String input) {
        return new Operation(parseOperands(input), parseOperator(input));
    }

    protected Operator parseOperator(String input) {
        if (input.contains("+")) {
            return new Addition();
        }
        if (input.contains("*")) {
            return new Multiplication();
        }
        if (input.contains("-")) {
            return new Subtraction();
        }
        if (input.contains("/")) {
            return new Division();
        }

        throw new IllegalArgumentException("no operator found");
    }

    protected boolean isNumberOfOperandsValid(String input) {
        return input.trim().split(regex).length <= 2;
    }

    protected int[] parseOperands(String input) {
        if (isNumberOfOperandsValid(input)) {
            return Arrays.stream(input.split(regex)).map(String::trim).mapToInt(Integer::parseInt).toArray();
        }
        throw new IllegalArgumentException("number of operands must not exceed 2");
    }
}
