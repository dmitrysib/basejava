package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> doTask("ResourceA", "ResourceB"), "Thread1");
        Thread thread2 = new Thread(() -> doTask("ResourceB", "ResourceA"), "Thread2");

        thread1.start();
        thread2.start();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void doTask(Object resource1,Object resource2) {
        synchronized (resource1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": lock resource " + resource1);

            DateUtil.sleep();

            System.out.println(threadName + ": waiting resource  " + resource2);

            synchronized (resource2) {
                System.out.println(threadName + ": lock resource  " + resource2);
            }
        }
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
