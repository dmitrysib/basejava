package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> doTask("A", "B"), "Thread1");
        Thread thread2 = new Thread(() -> doTask("B", "A"), "Thread2");

        thread1.start();
        thread2.start();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void doTask(Object object1, Object object2) {
        synchronized (object1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": lock object " + object1);

            TimeUtil.sleep();

            System.out.println(threadName + ": waiting object " + object2);

            synchronized (object2) {
                System.out.println(threadName + ": lock object " + object2);
            }
        }
    }

    static class TimeUtil {
        static void sleep() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
