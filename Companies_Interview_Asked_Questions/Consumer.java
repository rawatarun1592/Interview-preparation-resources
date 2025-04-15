package org.example.thread;

public class Consumer implements Runnable{
    Counter counter;
    Consumer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for(int i = 0 ; i< 1000; i++) {
            synchronized (counter) {
                while (counter.getFlag() == true) {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                    counter.get();
                    counter.setFlag(true);
                    counter.notify();

            }

        }
    }
}
