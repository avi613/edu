package avi.edu.dla.movement;

import avi.edu.dla.particle.Particle;
import avi.edu.dla.rand.RandomGenerator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Movement {
    Particle target;
    private RandomGenerator randomGenerator;

    public Particle move(Particle current) {
        System.out.println("before move, current position is: {x=" + current.getX() + ", y=" + current.getY() + "}");

        int[] nextMove = randomGenerator.nextMove();
        return target.equals(current) ? current : move(new Particle(current.getX() + nextMove[0], current.getY() + nextMove[1]));
    }
}
