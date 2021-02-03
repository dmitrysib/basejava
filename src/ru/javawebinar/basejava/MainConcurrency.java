package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {

        new Thread(() -> doTask("A", "B"), "Thread1").start();
        new Thread(() -> doTask("B", "A"), "Thread2").start();
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    private static void doTask(Object object1, Object object2) {
        synchronized (object1) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": lock object " + object1);

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }

            System.out.println(threadName + ": waiting object " + object2);

            synchronized (object2) {
                System.out.println(threadName + ": lock object " + object2);
            }
        }
    }
}
