package org.gfg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayDemo {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");
        stringList.add("xyz");
        stringList.add("ravi");
        System.out.println(stringList);
//        stringList.sort();
        Collections.sort(stringList);
        System.out.println(stringList);
    }

    private static void arrayDemo() {
        //data structure
        // algo
        int oldSize=2;
        int newSize=4;
        String[] students = new String[oldSize];

        students[0]="abc";
        students[1]="xyz";

        String[] temp = new String[4];
        for (int i=0; i<oldSize; i++){
            temp[i]=students[i];
        }

        students = temp;
        students[2]="ravi";
        for(String s: students){
            System.out.println(s);
        }
    }
}
