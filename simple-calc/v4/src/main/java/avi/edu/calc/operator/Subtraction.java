package avi.edu.calc.operator;

import java.util.Arrays;

public class Subtraction implements Operator {
    @Override
    public double operate(int[] numbers) {
        return numbers == null ? 0 : Arrays.stream(numbers).reduce(0, (a, b) -> a == 0 ? b : a - b);
    }
}
