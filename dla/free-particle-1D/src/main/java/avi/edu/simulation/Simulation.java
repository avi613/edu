package avi.edu.simulation;

import avi.edu.particle.Particle;

public class Simulation {
    public Particle simulate(int stepsNumbers, int initialPosition) {
        return stepsNumbers == 0 ? new Particle(initialPosition) : simulate(stepsNumbers - 1, initialPosition + 1);
    }
}
