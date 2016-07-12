package avi.edu.calc.operator;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class AdditionTest {
    private Addition addition = new Addition();

    @Test
    public void it_should_return_0_when_input_is_null() {
        assertThat(addition.operate(null)).isEqualTo(0);
    }

    @Test
    public void it_should_the_sole_element_list_add() {
        assertThat(addition.operate(new int[]{5})).isEqualTo(5);
    }

    @Test
    public void it_should_substract_negative_numbers() {
        assertThat(addition.operate(new int[] {1, -2})).isEqualTo(-1);
    }

    @Test
    public void it_should_return_the_sum_of_2_numbers() {
        assertThat(addition.operate(new int[]{1, 2})).isEqualTo(3);
    }
}
