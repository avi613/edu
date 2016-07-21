package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.rand.RandomGenerator;
import lombok.Value;

@Value
public class Simulation {
    private final int initialPosition;
    private final Movement movement;

    public void simulate() {
        movement.move(initialPosition);
    }

    public static void main(String[] args) {
        final int INITIAL_POSITION = 10;
        final int TARGET_POSITION = 0;
        Simulation simulation = new Simulation(INITIAL_POSITION, new Movement(TARGET_POSITION, new RandomGenerator()));
        simulation.simulate();
    }
}
