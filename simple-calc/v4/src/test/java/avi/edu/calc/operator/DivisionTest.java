package avi.edu.calc.operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivisionTest {
    private Division division = new Division();
    @Test
    public void it_should_return_0_when_multiply_input_is_null() {
        assertThat(division.operate(null)).isEqualTo(0);
    }

    @Test
    public void it_should_the_sole_element_list_multiply() {
        assertThat(division.operate(new int[]{8})).isEqualTo(8);
    }

    @Test
    public void it_should_divide_to_a_negative_number() {
        assertThat(division.operate(new int[]{2, -5})).isEqualTo(-2/5);
    }

    @Test(expected = ArithmeticException.class)
    public void it_should_throw_an_exception_if_dividend_is_0() {
        division.operate(new int[] {5, 0});
    }

    @Test
    public void it_should_return_the_division_of_2_numbers() {
        assertThat(division.operate(new int[]{2, 3})).isEqualTo(2/3);
    }
}
