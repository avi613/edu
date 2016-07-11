package avi.edu.calc;

import java.util.Arrays;

public class SimpleCalculator {
    public int add(String input) {
        if (input.trim().split(",").length > 2) {
            throw new IllegalArgumentException("number of addies must not exceed 2");
        }
        if (input.trim().isEmpty()) {
            return 0;
        }

        return Arrays.stream(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).sum();
    }
}
