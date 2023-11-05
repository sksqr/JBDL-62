package org.gfg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        int  corePoolSize  =    5;
        int  maxPoolSize   =   10;
        long keepAliveTime = 5000;

        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(
                        corePoolSize,
                        maxPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(50)
                );

        long start = System.currentTimeMillis();
        for(int i=0; i<1000; i++){
            threadPoolExecutor.submit(new MyTask("task-"+i));
        }
        threadPoolExecutor.submit(new MyTask("New-Task before shutdown"));
        threadPoolExecutor.submit(() -> System.out.println("Executing runnable task"));
        threadPoolExecutor.shutdown();
        threadPoolExecutor.submit(new MyTask("New-Task"));
        try {
            threadPoolExecutor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken: "+(end-start)+" ms");


    }
}

/*
t0: 5
t1: 5(no of task is low)
t2: 6-10(no of task is high)
t3: 10(no of task is very high)
t4: 10(no of task is very high)
t5: 10(no of task is low)
t6: 5(no of task is low) if (t6-t5 > keepAliveTime)
 */