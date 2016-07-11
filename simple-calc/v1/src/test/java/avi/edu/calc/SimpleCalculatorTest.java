package avi.edu.calc;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCalculatorTest {
    private SimpleCalculator calculator;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        calculator = new SimpleCalculator();
    }

    @Test
    public void it_should_return_0_when_input_is_empty() {
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void it_should_return_0_when_input_is_blank() {
        assertThat(calculator.add(" ")).isEqualTo(0);
    }

    @Test
    public void it_should_return_the_sole_element_of_a_single_element_list() {
        assertThat(calculator.add("5")).isEqualTo(5);
    }

    @Test
    public void it_should_subtract_negative_numbers() {
        assertThat(calculator.add("1,-2")).isEqualTo(-1);
    }

    @Test
    public void it_should_return_the_sum_of_2_numbers() {
        assertThat(calculator.add("1,2")).isEqualTo(3);
    }

    @Test
    public void it_should_throw_an_exception_when_number_of_addies_exceed_2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("number of addies must not exceed 2");
        calculator.add("1,2,3");
    }

    @Test(expected = NumberFormatException.class)
    public void it_should_throw_an_exception_when_addies_are_not_integers() {
        calculator.add("1.5,2.3");
    }

    @Test(expected = NumberFormatException.class)
    public void it_should_raise_an_exception_if_input_is_not_a_list_of_numbers() {
        calculator.add("any string");
    }
}
