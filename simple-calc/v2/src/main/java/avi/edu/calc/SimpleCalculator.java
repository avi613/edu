package avi.edu.calc;

import java.util.Arrays;

public class SimpleCalculator {
    private InputParser parser;

    public SimpleCalculator(InputParser parser) {
        this.parser = parser;
    }

    public int add(String input) {
        return input.trim().isEmpty() ? 0 : Arrays.stream(parser.parse(input)).sum();
    }

    public int multiply(String input) {
        return input.trim().isEmpty() ? 0 : Arrays.stream(parser.parse(input)).reduce(1, (a, b) -> a * b);
    }
}
