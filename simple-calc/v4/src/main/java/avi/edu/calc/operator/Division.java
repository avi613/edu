package avi.edu.calc.operator;

import java.util.Arrays;

public class Division implements Operator {
    @Override
    public double operate(int[] numbers) {
        return numbers == null ? 0 : Arrays.stream(numbers).reduce(1, (a, b) -> a == 1 ? b : a/b);
    }
}
