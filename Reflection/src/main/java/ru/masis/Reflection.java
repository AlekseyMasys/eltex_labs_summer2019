package ru.masis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;

public class Reflection {
    public static void main(String[] args) {
        Reflection linkedListInfo = new Reflection();
        linkedListInfo.showMethodsAndFields();
    }
    public void showMethodsAndFields() {
        Class clazz = LinkedList.class;
        System.out.println("Show LinkeList methods");
        Method[] methods = clazz.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);
        System.out.println("-------------------------------------------------------");
        System.out.println("Show LinkeList fields");
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::println);
    }
}
