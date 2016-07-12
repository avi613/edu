package avi.edu.calc;

import avi.edu.calc.operation.Operation;
import avi.edu.calc.operator.Addition;
import avi.edu.calc.operator.Division;
import avi.edu.calc.operator.Multiplication;
import avi.edu.calc.operator.Subtraction;
import avi.edu.calc.parser.InputParser;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleCalculatorTest {
    private InputParser parser;
    private SimpleCalculator calculator;

    @Before
    public void setUp() {
        parser = mock(InputParser.class);
        calculator = new SimpleCalculator(parser);
    }

    @Test
    public void it_should_return_the_sum_of_operands() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{1, 2}, new Addition()));
        assertThat(calculator.operate(anyString())).isEqualTo(3);
    }

    @Test
    public void it_should_return_the_multiplication_of_operands() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{5, 2}, new Multiplication()));
        assertThat(calculator.operate(anyString())).isEqualTo(10);
    }

    @Test
    public void it_should_multiply_to_a_negative_number() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{5, -2}, new Multiplication()));
        assertThat(calculator.operate(anyString())).isEqualTo(-10);
    }

    @Test
    public void it_should_subtract_operands() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{5, 2}, new Subtraction()));
        assertThat(calculator.operate(anyString())).isEqualTo(3);
    }

    @Test
    public void it_should_divide_operands() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{5, 2}, new Division()));
        assertThat(calculator.operate(anyString())).isEqualTo(5/2);
    }

    @Test(expected = ArithmeticException.class)
    public void it_should_throw_an_exception_when_dividend_is_0() {
        when(parser.parse(anyString())).thenReturn(new Operation(new int[]{5, 0}, new Division()));
        calculator.operate(anyString());
    }
}
