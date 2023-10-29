package com.test.interfacedemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AbstractionDemo {

    public static void main(String[] args) {
        System.out.println("Data");
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        list.add(20);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

}
