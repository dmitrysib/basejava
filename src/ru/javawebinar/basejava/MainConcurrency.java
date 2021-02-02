package ru.javawebinar.basejava;

import java.sql.ResultSet;

public class MainConcurrency {

    public static void main(String[] args) throws InterruptedException {

//        Simple deadlock with one resource
//        synchronized (MainConcurrency.class) {
//            Thread thread = new Thread(() -> {
//                System.out.println("Trying lock");
//                synchronized (MainConcurrency.class) {
//                    System.out.println("MainConcurrency locked!");
//                }
//            });
//            thread.start();
//            thread.join();
//        }

        Thread thread1 = new Thread(MainConcurrency::part1);
        Thread thread2 = new Thread(MainConcurrency::part2);

        thread1.start();
        thread2.start();
    }

    static class Resource {
        public int val = 1;
    }
    static final Resource RESOURCE_1 = new Resource();
    static final Resource RESOURCE_2 = new Resource();

    private static void part1() {
        synchronized (RESOURCE_1) {
            System.out.println("part1: lock resource 1");
            try {Thread.sleep(100); } catch (InterruptedException e) { }
            synchronized (RESOURCE_2) {
                System.out.println("part1: lock resource 2");
            }
        }
    }

    private static void part2() {
        synchronized (RESOURCE_2) {
            System.out.println("part2: lock resource 2");
            try {Thread.sleep(100); } catch (InterruptedException e) { }
            synchronized (RESOURCE_1) {
                System.out.println("part2: lock resource 1");
            }
        }
    }
}
