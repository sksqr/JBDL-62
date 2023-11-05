package org.gfg;


public class ThreadDemo {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        MyThread myThread = new MyThread();
        myThread.setName("Child-Thread-01");

        myThread.start();
//        myThread.run();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        method1();
        System.out.println("Done");
    }

    private static void method1(){
        int method1 = 1;
        System.out.println("method1");
        method2();
    }

    private static void method2(){
        int method2 = 2;
        System.out.println("method2");
    }
}
