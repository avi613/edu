package avi.edu.dla.simulation;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import lombok.Value;

@Value
public class Simulation {
    private RandomGenerator randomGenerator;

    public Particle simulate(int numberOfSteps, int initialPosition) {
        if (numberOfSteps < 0) {
            throw new IllegalArgumentException("Number of steps cannot be negative");
        }

        System.out.println("current position is: " + initialPosition);

        return numberOfSteps == 0 ? new Particle(initialPosition) : simulate(numberOfSteps - 1, initialPosition + randomGenerator.nextMove());
    }
}
