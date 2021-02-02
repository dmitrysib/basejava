package ru.javawebinar.basejava;

public class MainConcurrency {

    static final Resource RESOURCE_1 = new Resource();
    static final Resource RESOURCE_2 = new Resource();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            synchronized (RESOURCE_1) {
                System.out.println(Thread.currentThread().getName() + ": lock resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                synchronized (RESOURCE_2) {
                    System.out.println(Thread.currentThread().getName() + ": lock resource 2");
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (RESOURCE_2) {
                System.out.println(Thread.currentThread().getName() + ": lock resource 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                synchronized (RESOURCE_1) {
                    System.out.println(Thread.currentThread().getName() + ": lock resource 1");
                }
            }
        }, "Thread2");

        thread1.start();
        thread2.start();
    }

    static class Resource {
    }
}
