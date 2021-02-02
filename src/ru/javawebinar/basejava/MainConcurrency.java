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

        Thread thread1 = new Thread(MainConcurrency::task);
        Thread thread2 = new Thread(MainConcurrency::task);

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
            System.out.print("1 --> ");
            synchronized (RESOURCE_2) {
                System.out.println("2");
            }
        }
    }

    private static void part2() {
        synchronized (RESOURCE_2) {
            System.out.print("2 --> ");
            synchronized (RESOURCE_1) {
                System.out.println("1");
            }
        }
    }

    private static void task() {
        part1();
        part2();
    }
}
