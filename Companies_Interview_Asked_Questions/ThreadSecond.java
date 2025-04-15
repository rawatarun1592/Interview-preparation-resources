package org.example.thread;

public class ThreadSecond implements Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 5; i++) {
            System.out.println("Second Thread");
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
