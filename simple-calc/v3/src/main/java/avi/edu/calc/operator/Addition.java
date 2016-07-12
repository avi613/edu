package avi.edu.calc.operator;

import java.util.Arrays;

public class Addition implements Operator {
    @Override
    public int operate(int[] numbers) {
        return numbers == null ? 0 : Arrays.stream(numbers).sum();
    }
}
