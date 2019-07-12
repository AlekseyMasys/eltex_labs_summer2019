package ru.masis;

public class ThreadWithSynchronized implements Runnable {
    @Override
    public void run() {
        synchronized (Application.class) {
            for (int i = 0; i < 20; i++) {
                Application.count4--;
            }
        }
    }
}