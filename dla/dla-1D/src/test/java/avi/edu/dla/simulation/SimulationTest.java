package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.particle.Particle;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class SimulationTest {
    private final int INITIAL_POSITION = 10;

    private Movement movement = mock(Movement.class);
    private Simulation simulation = new Simulation(INITIAL_POSITION, movement);

    @Test
    public void movement_should_be_invoked_exactly_once() {
        when(movement.move(anyInt())).thenReturn(new Particle(42));
        simulation.simulate();
        verify(movement, times(1)).move(10);
    }
}
