package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> doTask("A", "B"), "Thread1");
        Thread thread2 = new Thread(() -> doTask("B", "A"), "Thread2");

        thread1.start();
        thread2.start();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void doTask(Object resource1,Object resource2) {
        synchronized (resource1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": lock object " + resource1);

            DateUtil.sleep();

            System.out.println(threadName + ": waiting object " + resource2);

            synchronized (resource2) {
                System.out.println(threadName + ": lock object " + resource2);
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
