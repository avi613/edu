package avi.edu.dla.simulation;

import avi.edu.dla.particle.Particle;

public class Simulation {
    public Particle simulate(String movement, int initialPosition) {
        if (movement.length() == 0) {
            return new Particle(initialPosition);
        }
        if (!movement.endsWith("+") && !movement.endsWith("-")) {
            throw new IllegalArgumentException("Movement not correctly formatted");
        }

        String nextMovement = movement.substring(0, movement.length() - 1);

        return movement.endsWith("+") ? simulate(nextMovement, initialPosition + 1) : simulate(nextMovement, initialPosition -1);
    }
}
