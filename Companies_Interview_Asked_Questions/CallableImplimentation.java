package org.example.thread;


import java.util.concurrent.Callable;

public class CallableImplimentation implements Callable<Boolean> {
    String str;
    CallableImplimentation(String str) {
        this.str = str;
    }
    @Override
    public Boolean call() throws Exception {
        return str.contains("@");
    }
}
