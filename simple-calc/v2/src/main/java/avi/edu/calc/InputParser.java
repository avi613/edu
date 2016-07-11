package avi.edu.calc;

import java.util.Arrays;

public class InputParser {
    public int[] parse(String input) {
        if (input.trim().split(",").length > 2) {
            throw new IllegalArgumentException("number of addies must not exceed 2");
        }
        return Arrays.stream(input.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
    }
}
