package edu.hw8.task2;

public class FibonacciCalculator implements Runnable {
    public int result;
    public int number;

    public FibonacciCalculator(int n) {
        this.number = n;
    }

    public static int calculate(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return calculate(n - 1) + calculate(n - 2);
    }

    @Override
    public void run() {
        result = calculate(number);
    }
}
