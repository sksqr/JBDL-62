package com.test;

import java.util.Date;

public class ClassObjectDemo {
    public static void main(String[] args) {
        method1();
        System.out.println("Done");
    }

    public static void method1(){

        Lecture l1 = new Lecture();
//        l1.createdDate = new Date();
//        l1.mentor = "ShashiKant";
        l1.setMentor("ShashiKant");
//        l1.status = "live";
//        l1.name ="Java Basics";
        System.out.println(l1);

        Lecture l2 = new Lecture();
//        l2.createdDate = new Date();
//        l2.mentor = "ShashiKant";
//        l2.status = "upcoming";
//        l2.name ="Java OOPs";
        System.out.println(l2);

        Lecture l3 = new Lecture("Exception handling", new Date(),"upcomming","ShashiKant");
//         l3.setCreatedDate(new Date());
        System.out.println(l3);

        System.out.println("Number Of Objects: "+Lecture.numberOfObjects );
    }
}
