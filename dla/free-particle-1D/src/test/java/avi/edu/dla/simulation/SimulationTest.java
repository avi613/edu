package avi.edu.dla.simulation;

import avi.edu.dla.particle.Particle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationTest {
    private Simulation simulation = new Simulation();

    @Test
    public void it_should_not_move_particle() {
        assertThat(simulation.simulate("", 36)).isEqualTo(new Particle(36));
    }

    @Test
    public void it_should_move_particle_one_up() {
        assertThat(simulation.simulate("+", 0)).isEqualTo(new Particle(1));
    }

    @Test
    public void it_should_move_particle_two_up() {
        assertThat(simulation.simulate("++", 0)).isEqualTo(new Particle(2));
    }

    @Test
    public void it_should_move_particle_one_up_from_any_initial_position() {
        assertThat(simulation.simulate("+", 2)).isEqualTo(new Particle(3));
    }

    @Test
    public void it_should_move_particle_one_down() {
        assertThat(simulation.simulate("-", 2)).isEqualTo(new Particle(1));
    }

    @Test
    public void it_should_move_particle_two_down() {
        assertThat(simulation.simulate("--", 2)).isEqualTo(new Particle(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void it_should_throw_an_exception_if_movement_is_not_well_formated() {
        simulation.simulate("+++.---", 1);
    }
}
