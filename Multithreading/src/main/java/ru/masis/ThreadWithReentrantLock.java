package ru.masis;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadWithReentrantLock implements Runnable{
    private ReentrantLock reentrantLock;


    public ThreadWithReentrantLock(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }
    @Override
    public void run() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < 20; i++) {
                Application.count5--;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reentrantLock.unlock();
        }
    }
}
