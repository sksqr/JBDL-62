package org.gfg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        long start = System.currentTimeMillis();
        for(int i=0; i<1000; i++){
            executorService.submit(new MyTask("task-"+i));
        }
        executorService.submit(new MyTask("New-Task before shutdown"));
        executorService.shutdown();
        executorService.submit(new MyTask("New-Task"));
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken: "+(end-start)+" ms");

        System.out.println(Runtime.getRuntime().availableProcessors());



    }
}
