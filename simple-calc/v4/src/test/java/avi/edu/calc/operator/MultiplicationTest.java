package avi.edu.calc.operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplicationTest {
    private Multiplication multiplication = new Multiplication();

    @Test
    public void it_should_return_0_when_multiply_input_is_null() {
        assertThat(multiplication.operate(null)).isEqualTo(0);
    }

    @Test
    public void it_should_the_sole_element_list_multiply() {
        assertThat(multiplication.operate(new int[]{8})).isEqualTo(8);
    }

    @Test
    public void it_should_multiply_to_a_negative_number() {
        assertThat(multiplication.operate(new int[]{2, -5})).isEqualTo(-10);
    }

    @Test
    public void it_should_return_the_multiplication_of_2_numbers() {
        assertThat(multiplication.operate(new int[]{2, 3})).isEqualTo(6);
    }
}
