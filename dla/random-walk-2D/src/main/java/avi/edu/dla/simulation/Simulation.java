package avi.edu.dla.simulation;

import avi.edu.dla.movement.Movement;
import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import lombok.Value;

import java.util.Random;

@Value
public class Simulation {
    private final Particle initial;
    private final Movement movement;

    public void simulate() {
        movement.move(initial);
    }

    public static void main(String[] args) {
        final Particle INITIAL = new Particle(10, 10);
        final Particle TARGET = new Particle(0, 0);
        Simulation simulation = new Simulation(INITIAL, new Movement(TARGET, new RandomGenerator(new Random())));
        simulation.simulate();
    }
}
