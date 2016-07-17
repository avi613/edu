package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.rand.RandomGenerator;
import lombok.Value;

@Value
public class Simulation {
    private final int initialPosition;
    private final int targetPosition;
    private final Movement movement;

    public void simulate() {
        movement.move(initialPosition);
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation(10, 0, new Movement(0, new RandomGenerator()));
        simulation.simulate();
    }
}
