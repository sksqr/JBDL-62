package org.gfg;

import java.util.concurrent.atomic.AtomicInteger;

public class VisitorCounterTask implements Runnable {
    private int count;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

//    private void increment(){
//        count++;
//        atomicInteger.incrementAndGet();
//    }

    private synchronized void increment(){
        count++;
    }

    @Override
    public void run() {
        increment();
    }

    @Override
    public String toString() {
        return "VisitorCounterTask{" +
                "count=" + count +
                ", atomicInteger=" + atomicInteger +
                '}';
    }
}
