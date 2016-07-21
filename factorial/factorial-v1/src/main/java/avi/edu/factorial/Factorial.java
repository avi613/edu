package avi.edu.factorial;

public class Factorial {
    public int of(int number) {
        if (number == 1) return number;
        return number * of(number - 1);
    }
}
