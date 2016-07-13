package avi.edu.fibonacci;

public class Fibonacci {
    public int getFibonacciNumberAtPosition(int position) {
        return position < 2 ? position : getFibonacciNumberAtPosition(position - 1) + getFibonacciNumberAtPosition(position - 2);
    }
}
