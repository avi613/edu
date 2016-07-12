package avi.edu.calc.operation;

import avi.edu.calc.operator.Operator;
import lombok.Value;

@Value
public class Operation {
    private int[] operands;
    private Operator operator;
}
