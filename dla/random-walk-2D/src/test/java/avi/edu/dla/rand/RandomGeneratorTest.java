package avi.edu.dla.rand;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomGeneratorTest {
    private Random random = mock(Random.class);
    private RandomGenerator randomGenerator = new RandomGenerator(random);
    private List<int[]> moves = ImmutableList.of(
            new int[] {1, 0},
            new int[] {-1, 0},
            new int[] {0, 1},
            new int[] {0, -1}
    );

    @Test
    public void should_generate_move_one_est() {
        when(random.nextInt(4)).thenReturn(0);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get(0));
    }

    @Test
    public void should_generate_move_one_west() {
        when(random.nextInt(4)).thenReturn(1);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get(1));
    }

    @Test
    public void should_generate_move_one_north() {
        when(random.nextInt(4)).thenReturn(2);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get(2));
    }

    @Test
    public void should_generate_move_one_south() {
        when(random.nextInt(4)).thenReturn(3);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get(3));
    }
}
