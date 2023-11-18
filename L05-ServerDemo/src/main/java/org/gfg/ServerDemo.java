package org.gfg;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerDemo {

    public static void main(String[] args) {
        System.out.println("Starting server");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Scanner sc = new Scanner(System.in);

        while (true) {
            String data = sc.nextLine();

            if("exit".equals(data)){
                System.exit(0);
            }
            executorService.submit(() -> {   try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
                System.out.println("Processing data " + data +" in thread:"+Thread.currentThread().getName());});

        }

    }
}
