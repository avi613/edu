package avi.edu.dla.rand;

import com.google.common.collect.ImmutableBiMap;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomGeneratorTest {
    private Random random = mock(Random.class);
    private RandomGenerator randomGenerator = new RandomGenerator(random);
    private Map<String, int[]> moves = ImmutableBiMap.of(
            "EST", new int[] {1, 0},
            "WEST", new int[] {-1, 0},
            "NORTH", new int[] {0, 1},
            "SOUTH", new int[] {0, -1}
    );

    @Test
    public void should_generate_move_one_est() {
        when(random.nextInt(4)).thenReturn(0);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get("EST"));
    }

    @Test
    public void should_generate_move_one_west() {
        when(random.nextInt(4)).thenReturn(1);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get("WEST"));
    }

    @Test
    public void should_generate_move_one_north() {
        when(random.nextInt(4)).thenReturn(2);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get("NORTH"));
    }

    @Test
    public void should_generate_move_one_south() {
        when(random.nextInt(4)).thenReturn(3);
        assertThat(randomGenerator.nextMove()).isEqualTo(moves.get("SOUTH"));
    }
}
