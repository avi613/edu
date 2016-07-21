package avi.edu.dla.rand;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private final int NUMBER_OF_DIRECTIONS = 4;
    private Random random;
    private List<int[]> moves = ImmutableList.of(
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0, 1},
            new int[]{0, -1}
    );

    public RandomGenerator(Random random) {
        this.random = random;
    }

    public int[] nextMove() {
        return moves.get(random.nextInt(NUMBER_OF_DIRECTIONS));
    }
}
