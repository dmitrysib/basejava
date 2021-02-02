package ru.javawebinar.basejava;

public class MainConcurrency {

    static final Resource RESOURCE_1 = new Resource();
    static final Resource RESOURCE_2 = new Resource();

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            synchronized (RESOURCE_1) {
                System.out.println(Thread.currentThread().getName() + ": lock resource 1");
                DateUtil.sleep();
                System.out.println(Thread.currentThread().getName() + ": waiting resource 2");
                synchronized (RESOURCE_2) {
                    System.out.println(Thread.currentThread().getName() + ": lock resource 2");
                }
            }
        }, "Thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (RESOURCE_2) {
                System.out.println(Thread.currentThread().getName() + ": lock resource 2");
                DateUtil.sleep();
                System.out.println(Thread.currentThread().getName() + ": waiting resource 1");
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

    static class DateUtil {
        static void sleep() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
