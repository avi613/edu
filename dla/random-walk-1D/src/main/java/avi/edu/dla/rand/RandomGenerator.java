package avi.edu.dla.rand;

import java.util.Random;

public class RandomGenerator {
    private Random random;

    public RandomGenerator() {
        random = new Random();
    }

    public int nextMove() {
        return random.nextInt(2);
    }
}
