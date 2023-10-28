package com.paytm.paymentservice;

import com.test.Lecture;
import com.test.Person;

public class ProtectedDemo {

    public static void main(String[] args) {
        Lecture l1 = new Lecture();
//        System.out.println("Number Of Objects: "+Lecture.numberOfObjects );


        Person p2 = new Person();
        p2.setName("Ravi");
        //p1.setSubject("Maths");
//        p2.age=21;
        p2.walk();
    }
}
