package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCounter {
    private AtomicInteger counter;

    public MultiThreadCounter() {
        counter = new AtomicInteger(0);
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }
}
