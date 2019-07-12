package ru.masis;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
    public static Integer count1 = 1000; // field to decrement with  the help of ThreadWithoutSynchronization
    public static volatile Integer count2 = 1000; // field to decrement with  the help of ThreadWithoutSynchronization
    public static AtomicInteger count3 = new AtomicInteger(1000); // field to decrement with  the help of AtomicThread
    public static Integer count4 = 1000; // field to decrement with  the help of ThreadWithSinchronized
    public static Integer count5 = 1000; // field to decrement with  the help of ThreadWithReentrantLock
    public static Integer count6 = 1000; // field to decrement with  the help of ThreadWithSemaphore
    public static Integer count7 = 1000; //field to decrement with  the help of ThreadPool


    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadWithoutSynchronization());
        Thread thread2 = new Thread(new ThreadWithoutSynchronization());
        Thread thread3 = new Thread(new ThreadWithoutSynchronization());
        Thread thread4 = new Thread(new ThreadWithoutSynchronization());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count1);

        Thread thread5 = new Thread(new ThreadWithVolatile());
        Thread thread6 = new Thread(new ThreadWithVolatile());
        Thread thread7 = new Thread(new ThreadWithVolatile());
        Thread thread8 = new Thread(new ThreadWithVolatile());
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count2);




        Thread thread9 = new Thread(new AtomicThread());
        Thread thread10 = new Thread(new AtomicThread());
        Thread thread11 = new Thread(new AtomicThread());
        Thread thread12 = new Thread(new AtomicThread());
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count3);


        Thread thread13 = new Thread(new ThreadWithSynchronized());
        Thread thread14 = new Thread(new ThreadWithSynchronized());
        Thread thread15 = new Thread(new ThreadWithSynchronized());
        Thread thread16 = new Thread(new ThreadWithSynchronized());
        thread13.start();
        thread14.start();
        thread15.start();
        thread16.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count4);

        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread17 = new Thread(new ThreadWithReentrantLock(reentrantLock));
        Thread thread18 = new Thread(new ThreadWithReentrantLock(reentrantLock));
        Thread thread19 = new Thread(new ThreadWithReentrantLock(reentrantLock));
        Thread thread20 = new Thread(new ThreadWithReentrantLock(reentrantLock));
        thread17.start();
        thread18.start();
        thread19.start();
        thread20.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count5);

        Semaphore semaphore = new Semaphore(2);

        Thread thread21 = new Thread(new ThreadWithSemaphore(semaphore));
        Thread thread22 = new Thread(new ThreadWithSemaphore(semaphore));
        Thread thread23 = new Thread(new ThreadWithSemaphore(semaphore));
        Thread thread24 = new Thread(new ThreadWithSemaphore(semaphore));
        thread21.start();
        thread22.start();
        thread23.start();
        thread24.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count6);

        ThreadPoolTask threadPoolTask = new ThreadPoolTask();
        threadPoolTask.decrement();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count7);

    }

}
