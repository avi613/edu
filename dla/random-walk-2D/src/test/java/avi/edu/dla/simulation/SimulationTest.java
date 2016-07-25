package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.movement.Trampoline;
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
        Trampoline<Particle> trampoline = new Trampoline<Particle>() {
            public Particle get() {
                return new Particle(42, 36);
            }
        };
        when(movement.move(any(Particle.class))).thenReturn(trampoline);
        simulation.simulate();
        verify(movement, times(1)).move(initial);
    }
}
