package com.test.interfacedemo;

import java.util.Objects;
import java.util.Scanner;

public class PolymorphismDemo {
    public static void main(String[] args) {
        //Overloading
        AreaCalculator areaCalculator = new AreaCalculator();
        areaCalculator.area(2.2);

        //Overriding
        Person p1 = null;
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        if(input.equals("T")){
            p1 = new Teacher();
        }
        else{
            p1 = new Person();
        }
        p1.walk();

        System.out.println(p1.toString());
    }
    String a ="";
}

class A{

}
