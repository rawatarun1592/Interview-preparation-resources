package org.example.thread;

public class ThreadFirst implements Runnable{
    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("First Thread ...");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
