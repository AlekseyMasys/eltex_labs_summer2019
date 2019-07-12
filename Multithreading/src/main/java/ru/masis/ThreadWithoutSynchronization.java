package ru.masis;

public class ThreadWithoutSynchronization implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            Application.count1--;
        }
    }
}