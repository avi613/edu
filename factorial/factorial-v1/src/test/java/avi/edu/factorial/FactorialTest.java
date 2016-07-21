package avi.edu.factorial;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactorialTest {
    private Factorial factorial = new Factorial();

    @Test
    public void factorial_1_should_be_1() {
        assertThat(factorial.of(1)).isEqualTo(1);
    }

    @Test
    public void factorial_2_should_be_2() {
        assertThat(factorial.of(2)).isEqualTo(2);
    }

    @Test
    public void factorial_3_should_be_6() {
        assertThat(factorial.of(3)).isEqualTo(6);
    }

    @Test
    public void factorial_4_should_be_24() {
        assertThat(factorial.of(4)).isEqualTo(24);
    }
}
