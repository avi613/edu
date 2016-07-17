package avi.edu.dla.movement;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovementTest {
    private final int TARGET_POSITION = 0;
    private RandomGenerator randomGenerator = mock(RandomGenerator.class);
    private Movement movement = new Movement(TARGET_POSITION, randomGenerator);

    @Test
    public void particle_should_not_move_when_target_reached() {
        assertThat(movement.move(0)).isEqualTo(new Particle(0));
    }

    @Test
    public void particle_should_move_one_up() {
        when(randomGenerator.nextMove()).thenReturn(1);
        assertThat(movement.move(-1)).isEqualTo(new Particle(0));
    }

    @Test
    public void particle_should_move_one_down() {
        when(randomGenerator.nextMove()).thenReturn(-1);
        assertThat(movement.move(1)).isEqualTo(new Particle(0));
    }
}
