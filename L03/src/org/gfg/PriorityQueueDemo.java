package org.gfg;

import java.util.*;

public class PriorityQueueDemo {

    public static void main(String[] args) {

        Queue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        heap.add(20);
        heap.add(1);
        heap.add(3);
        heap.add(40);
        System.out.println(heap.peek());
        System.out.println(heap.peek());
        System.out.println(heap.poll());
        System.out.println(heap.peek());

        //Queue<Student> studentQueue = new PriorityQueue<>((o1, o2) -> o1.getName().compareTo(o2.getName()));

//        Queue<Student> studentQueue = new PriorityQueue<>(Comparator.reverseOrder());
//
//        studentQueue.add(new Student(4,"Rahul"));
//        studentQueue.add(new Student(1,"Rani"));
//        studentQueue.add(new Student(12,"Ravi"));
//
//        System.out.println(studentQueue.peek());
//        System.out.println(studentQueue.poll());
//        System.out.println(studentQueue.peek());


        Queue<Student> studentQueue = new PriorityQueue<>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        studentQueue.add(new Student(4,"xyz"));
        studentQueue.add(new Student(1,"abc"));
        studentQueue.add(new Student(12,"bcd"));
        studentQueue.add(new Student(13,"kcd"));
        studentQueue.add(new Student(11,"efg"));
        studentQueue.add(new Student(14,"lcd"));
        studentQueue.add(new Student(24,"gcd"));
        for (Student student: studentQueue){
            System.out.println(student);
        }


    }
}
