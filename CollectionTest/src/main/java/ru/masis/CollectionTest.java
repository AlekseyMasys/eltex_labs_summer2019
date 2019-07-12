package ru.masis;

import java.util.ArrayList;

public class CollectionTest {
    public User generateRandomUser() {
        User user = null;
        Integer number = (int) (Math.random()*2);
        switch (number) {
            case 0: {
                Developer developer = new Developer();
                user = developer;
            }
            case 1: {
                Manager manager = new Manager();
                user = manager;
            }
        }
        return user;
    }
    public void testArrayList() {
        ArrayList<User> users = new ArrayList<>();
        long timeOfAdd;
        long timeOfRemove;
        long timeOfSearch;
        long start;
        long end;

        start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            users.add(generateRandomUser());
        }
        end = System.currentTimeMillis();
        timeOfAdd = end - start;
        System.out.println("add time 200000 items " + timeOfAdd + "ms");

        start = System.currentTimeMillis();
        for (int i = 100000; i < 150000; i++) {
            users.remove(i);
        }
        end = System.currentTimeMillis();
        timeOfRemove = end - start;

        System.out.println("removal time of 50000 items " + timeOfRemove + "ms");

        start = System.currentTimeMillis();
        User user = users.get(users.size()/2);
        end = System.currentTimeMillis();
        timeOfSearch = end - start;

        System.out.println("item search time in the middle of the collection " + timeOfSearch + "ms");
    }

    public void testLinkedList() {
        ArrayList<User> users = new ArrayList<>();
        long timeOfAdd;
        long timeOfRemove;
        long timeOfSearch;
        long start;
        long end;

        start = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            users.add(generateRandomUser());
        }
        end = System.currentTimeMillis();
        timeOfAdd = end - start;
        System.out.println("add time 200000 items " + timeOfAdd + "ms");

        start = System.currentTimeMillis();
        for (int i = 100000; i < 150000; i++) {
            users.remove(i);
        }
        end = System.currentTimeMillis();
        timeOfRemove = end - start;

        System.out.println("removal time of 50000 items " + timeOfRemove + "ms");

        start = System.currentTimeMillis();
        User user = users.get(users.size()/2);
        end = System.currentTimeMillis();
        timeOfSearch = end - start;

        System.out.println("item search time in the middle of the collection " + timeOfSearch + "ms");
    }

    public static void main(String[] args) {
        CollectionTest testCollection = new CollectionTest();
        System.out.println("Arraylist test");
        testCollection.testArrayList();
        System.out.println("-----------------------------------------------------");
        System.out.println("LinkedList test");
        testCollection.testLinkedList();
    }
}
