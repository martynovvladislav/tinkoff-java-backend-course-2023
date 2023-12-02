package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsAmount;
    private final Thread[] threads;
    private final BlockingQueue<Runnable> blockingQueue;

    public FixedThreadPool(int threadsAmount) {
        this.threadsAmount = threadsAmount;
        this.threads = new Thread[threadsAmount];
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsAmount; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    Runnable task = blockingQueue.poll();
                    if (task == null) {
                        continue;
                    }
                    task.run();
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            blockingQueue.put(runnable);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
