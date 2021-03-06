package ru.javawebinar.basejava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainConcurrency {
    public static final int THREAD_NUMBERS = 10_000;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBERS);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < THREAD_NUMBERS; i++) {
            executorService.submit(() -> {
                for (int y = 0; y < 100; y++) {
                    mainConcurrency.inc();
                }
                latch.countDown();
                return 5;
            });
        }

        latch.await();
        executorService.shutdown();
        System.out.println(mainConcurrency.atomicCounter.get());

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

    private void inc() {
        atomicCounter.incrementAndGet();
    }
}
