package ru.masis;

import java.util.concurrent.Semaphore;

public class ThreadWithSemaphore implements Runnable {

    private Semaphore semaphore;

    public ThreadWithSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 20; i++) {
                Application.count6--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            semaphore.release();
        }
    }
}
