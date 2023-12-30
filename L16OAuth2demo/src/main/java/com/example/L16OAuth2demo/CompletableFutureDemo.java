package com.example.L16OAuth2demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> completableFuture = (CompletableFuture<Void>) executorService.submit(() -> System.out.println("Thread name:"+Thread.currentThread().getName()));
        completableFuture.

    }
}
