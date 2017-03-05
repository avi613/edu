package avi.edu.checkout;

public class Checkout {
    private int total = 0;

    public void add(int count, int price) {
        total += count * price;
    }

    public int total() {
        return total;
    }
}
