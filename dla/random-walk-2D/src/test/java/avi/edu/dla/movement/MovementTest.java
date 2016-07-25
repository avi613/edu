package avi.edu.dla.movement;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import com.google.common.collect.ImmutableBiMap;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest {
    private final int TARGET_POSITION_X = 0;
    private final int TARGET_POSITION_Y = 0;
    private final Particle finalPositionParticle = new Particle(TARGET_POSITION_X, TARGET_POSITION_Y);

    private RandomGenerator randomGenerator = mock(RandomGenerator.class);
    private Map<String, int[]> moves = ImmutableBiMap.of(
            "EST", new int[] {1, 0},
            "WEST", new int[] {-1, 0},
            "NORTH", new int[] {0, 1},
            "SOUTH", new int[] {0, -1}
    );

    private Movement movement = new Movement(finalPositionParticle, randomGenerator);

    @Test
    public void particle_should_not_move_when_target_is_reached() {
        assertThat(movement.move(new Particle(0, 0)).execute()).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_est() {
        when(randomGenerator.nextMove()).thenReturn(moves.get("EST"));
        assertThat(movement.move(new Particle(-1, 0)).execute()).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_west() {
        when(randomGenerator.nextMove()).thenReturn(moves.get("WEST"));
        assertThat(movement.move(new Particle(1, 0)).execute()).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_north() {
        when(randomGenerator.nextMove()).thenReturn(moves.get("NORTH"));
        assertThat(movement.move(new Particle(0, -1)).execute()).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_south() {
        when(randomGenerator.nextMove()).thenReturn(moves.get("SOUTH"));
        assertThat(movement.move(new Particle(0, 1)).execute()).isEqualTo(finalPositionParticle);
    }
}
