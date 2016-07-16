package avi.edu.dla.Simulation;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import avi.edu.dla.simulation.Simulation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class SimulationTest {
    private RandomGenerator randomGenerator = mock(RandomGenerator.class);
    private Simulation simulation = new Simulation(randomGenerator);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void it_should_not_move_particle() {
        when(randomGenerator.nextMove()).thenReturn(64);
        assertThat(simulation.simulate(0, 36)).isEqualTo(new Particle(36));
    }

    @Test
    public void it_should_move_particle_one_up() {
        when(randomGenerator.nextMove()).thenReturn(1);
        assertThat(simulation.simulate(1, 3)).isEqualTo(new Particle(4));
    }

    @Test
    public void it_should_move_particle_one_down() {
        when(randomGenerator.nextMove()).thenReturn(-1);
        assertThat(simulation.simulate(1, 4)).isEqualTo(new Particle(3));
    }

    @Test
    public void it_should_not_accept_negative_number_of_steps() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Number of steps cannot be negative");
        simulation.simulate(-1, 36);
    }

    @Test
    public void it_should_move_particle_three_times() {
        simulation.simulate(3, 24);
        verify(randomGenerator, times(3)).nextMove();
    }

    @Test
    public void it_should_move_particle_within_a_range() {
        Simulation realSimulation = new Simulation(new RandomGenerator());
        assertThat(realSimulation.simulate(3, 0).getPosition()).matches(position -> position >= -3 && position <= 3);
    }
}
