package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.particle.Particle;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SimulationTest {
    private final Particle initial = new Particle(10, 10);

    private Movement movement = mock(Movement.class);
    private Simulation simulation = new Simulation(initial, movement);

    @Test
    public void movement_should_be_invoked_only_once() {
        when(movement.move(any(Particle.class))).thenReturn(new Particle(42, 36));
        simulation.simulate();
        verify(movement, times(1)).move(initial);
    }
}
