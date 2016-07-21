package avi.edu.dla.movement;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest {
    private final int TARGET_POSITION_X = 0;
    private final int TARGET_POSITION_Y = 0;
    private final Particle finalPositionParticle = new Particle(TARGET_POSITION_X, TARGET_POSITION_Y);

    private RandomGenerator randomGenerator = mock(RandomGenerator.class);
    private List<int[]> moves = ImmutableList.of(
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1}
    );

    private Movement movement = new Movement(finalPositionParticle, randomGenerator);

    @Test
    public void particle_should_not_move_when_target_is_reached() {
        assertThat(movement.move(new Particle(0, 0))).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_est() {
        when(randomGenerator.nextMove()).thenReturn(moves.get(0));
        assertThat(movement.move(new Particle(-1, 0))).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_west() {
        when(randomGenerator.nextMove()).thenReturn(moves.get(1));
        assertThat(movement.move(new Particle(1, 0))).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_north() {
        when(randomGenerator.nextMove()).thenReturn(moves.get(2));
        assertThat(movement.move(new Particle(0, -1))).isEqualTo(finalPositionParticle);
    }

    @Test
    public void particle_should_move_one_south() {
        when(randomGenerator.nextMove()).thenReturn(moves.get(3));
        assertThat(movement.move(new Particle(0, 1))).isEqualTo(finalPositionParticle);
    }
}
