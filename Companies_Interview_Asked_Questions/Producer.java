package org.example.thread;

public class Producer implements Runnable{
    Counter counter;
    Producer(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {

        for(int i = 0; i < 1000; i++) {
            synchronized (counter) {
                while(counter.getFlag() == false) {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                    System.out.print("count set to : ");
                    counter.get();
                    counter.increment();
                    counter.setFlag(false);
                    counter.notify();
                }

        }
    }
}
