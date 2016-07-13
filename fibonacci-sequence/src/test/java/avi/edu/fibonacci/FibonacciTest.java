package avi.edu.fibonacci;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FibonacciTest {
    private Fibonacci fibonacci = new Fibonacci();

    @Test
    public void first_fibonacci_number_should_be_0() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(0)).isEqualTo(0);
    }

    @Test
    public void second_fibonacci_number_should_be_1() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(1)).isEqualTo(1);
    }

    @Test
    public void third_fibonacci_number_should_be_1() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(2)).isEqualTo(1);
    }

    @Test
    public void fourth_fibonacci_number_should_be_2() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(3)).isEqualTo(2);
    }

    @Test
    public void sixth_fibonacci_number_should_be_5() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(5)).isEqualTo(5);
    }

    @Test
    public void seventh_fibonacci_number_should_be_8() {
        assertThat(fibonacci.getFibonacciNumberAtPosition(6)).isEqualTo(8);
    }
}
