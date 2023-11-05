package org.gfg;

public class RunnableDemo {

    public static void main(String[] args) {

        MyTask task1 = new MyTask("Task-1");
        //task1.run();
        Thread thread = new Thread(task1);
        thread.start();

        Thread thread2 = new Thread(task1);
        thread2.start();

        try {
            thread.join();
            thread2.join();
            task1.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done");

    }
}
