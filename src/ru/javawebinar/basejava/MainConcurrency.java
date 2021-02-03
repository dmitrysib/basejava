package ru.javawebinar.basejava;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    public static final int THREAD_NUMBERS = 10_000;
    private int counter;
    private AtomicInteger atomicCounter = new AtomicInteger();

    private static final SimpleDateFormat sdf = new SimpleDateFormat();

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREAD_NUMBERS);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);

        for (int i = 0; i < THREAD_NUMBERS; i++) {
            Future<Integer> future = executorService.submit(() -> {
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

//        new Thread(() -> doTask("A", "B"), "Thread1").start();
//        new Thread(() -> doTask("B", "A"), "Thread2").start();
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
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
    }
}
