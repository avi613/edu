package avi.edu.dla.rand;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomGeneratorTest {
    private RandomGenerator randomGenerator = new RandomGenerator();

    @Test
    public void it_should_generate_random_ints_between_0_and_1() {
        assertThat(randomGenerator.nextMove()).matches(nextMove -> nextMove == -1 || nextMove == 1);
    }
}
