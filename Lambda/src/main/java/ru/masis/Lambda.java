package ru.masis;

import java.util.ArrayList;
import java.util.List;

public class Lambda {
    public static void main(String[] args) {
        sumWithLambda();

    }
    public static void sumWithLambda() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        System.out.println(list.stream().map(elem -> elem*elem).reduce(0,(left, right) -> left + right));
    }
}
