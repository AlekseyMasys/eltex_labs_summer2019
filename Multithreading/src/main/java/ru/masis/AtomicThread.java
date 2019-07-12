package ru.masis;

public class AtomicThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Application.count3.decrementAndGet();
        }
    }
}

