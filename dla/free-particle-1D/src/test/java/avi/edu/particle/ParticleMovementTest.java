package avi.edu.particle;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticleMovementTest {
    private ParticleMovement movement = new ParticleMovement();

    @Test
    public void it_should_move_particle_plus_1() {
        assertThat(movement.movePositive(new Particle(0))).isEqualToComparingFieldByField(new Particle(1));
        assertThat(movement.movePositive(new Particle(1))).isEqualToComparingFieldByField(new Particle(2));
    }

    @Test
    public void it_should_move_particle_minus_1() {
        assertThat(movement.moveNegative(new Particle(0))).isEqualToComparingFieldByField(new Particle(-1));
        assertThat(movement.moveNegative(new Particle(-1))).isEqualToComparingFieldByField(new Particle(-2));
    }
}
