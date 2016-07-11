package avi.edu.calc;

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
    public void it_should_return_0_when_add_input_is_empty() {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void it_should_return_0_when_multiply_input_is_blank() {
        assertThat(calculator.multiply("  ")).isEqualTo(0);
    }

    @Test
    public void it_should_the_sole_element_list_add() {
        when(parser.parse(anyString())).thenReturn(new int[]{5});
        assertThat(calculator.add("5")).isEqualTo(5);
    }

    @Test
    public void it_should_the_sole_element_list_multiply() {
        when(parser.parse(anyString())).thenReturn(new int[]{8});
        assertThat(calculator.multiply("8")).isEqualTo(8);
    }

    @Test
    public void it_should_substract_negative_numbers() {
        when(parser.parse(anyString())).thenReturn(new int[]{1, -2});
        assertThat(calculator.add("1,-2")).isEqualTo(-1);
    }

    @Test
    public void it_should_multiply_to_a_negative_number() {
        when(parser.parse(anyString())).thenReturn(new int[] {2, -5});
        assertThat(calculator.multiply("some input")).isEqualTo(-10);
    }

    @Test
    public void it_should_return_the_sum_of_2_numbers() {
        when(parser.parse(anyString())).thenReturn(new int[]{1, 2});
        assertThat(calculator.add("1,2")).isEqualTo(3);
    }

    @Test
    public void it_should_return_the_multiplication_of_2_numbers() {
        when(parser.parse(anyString())).thenReturn(new int[]{2, 3});
        assertThat(calculator.multiply("2,3")).isEqualTo(6);
    }
}
