package avi.edu.calc.operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtractionTest {
    private Subtraction subtraction = new Subtraction();

    @Test
    public void it_should_return_0_when_input_is_null() {
        assertThat(subtraction.operate(null)).isEqualTo(0);
    }

    @Test
    public void it_should_the_sole_element_list_multiply() {
        assertThat(subtraction.operate(new int[]{8})).isEqualTo(8);
    }

    @Test
    public void it_should_substract_2_numbers() {
        assertThat(subtraction.operate(new int[]{2, 3})).isEqualTo(-1);
    }
}
