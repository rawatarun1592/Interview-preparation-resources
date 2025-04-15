package org.example.thread;

public class Counter {
    int count;
    boolean flag = true;
    Counter(int count) {
       this.count = count;
    }
    void increment() {
        count++;
    }
    void get() {
        System.out.println("count value is : " + count);
    }
    void setFlag(boolean flag) {
        this.flag = flag;
    }
    boolean getFlag() {
        return this.flag;
    }
}
