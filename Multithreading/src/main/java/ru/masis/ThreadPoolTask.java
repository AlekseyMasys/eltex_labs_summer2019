package ru.masis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTask {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    public void decrement() {
        for (int i = 0; i < 4; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 20; i++) {
                        Application.count7--;
                    }
                }
            });
        }
        executorService.shutdown();
    }
}

