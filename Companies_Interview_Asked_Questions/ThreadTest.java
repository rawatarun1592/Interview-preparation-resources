package org.example.thread;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {
    public static void main(String[] args) {
        // publisher subscriber problem (communication between two thread)
//        Counter counter = new Counter(0);
//        Thread thread1 = new Thread(new Producer(counter));
//        Thread thread2 = new Thread(new Consumer(counter));
//        thread1.start();
//        thread2.start();
//        try {thread1.join(); thread2.join();} catch (Exception e) {}
//        counter.get();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Boolean> f1 = executorService.submit(new CallableImplimentation("ranjan@123"));
        //executorService.shutdown();
        try {
            System.out.print(f1.get());
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
