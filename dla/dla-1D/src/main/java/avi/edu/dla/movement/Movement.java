package avi.edu.dla.movement;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Movement {
    private int targetPosition;
    private RandomGenerator randomGenerator;

    public Particle move(int currentPosition) {
        System.out.println("before move, current position is: " + currentPosition);
        return currentPosition == targetPosition ? new Particle(currentPosition) : move(currentPosition + randomGenerator.nextMove());
    }
}
