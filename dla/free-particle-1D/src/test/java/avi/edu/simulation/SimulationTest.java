package avi.edu.simulation;

import avi.edu.particle.Particle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {
    private Simulation simulation = new Simulation();

    @Test
    public void it_should_move_particle_one_up() {
        assertThat(simulation.simulate(1, 0)).isEqualTo(new Particle(1));
    }

    @Test
    public void it_should_move_particle_two_up() {
        assertThat(simulation.simulate(2, 0)).isEqualTo(new Particle(2));
    }

    @Test
    public void it_should_move_particle_one_up_from_any_initial_position() {
        assertThat(simulation.simulate(1, 2)).isEqualTo(new Particle(3));
    }
}
