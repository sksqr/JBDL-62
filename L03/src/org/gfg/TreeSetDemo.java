package org.gfg;

import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {

//        Set<Student> studentSet = new HashSet<>();
//        studentSet.add(new Student(4,"Rahul"));
//        studentSet.add(new Student(1,"Rani"));
//        studentSet.add(new Student(12,"Ravi"));
//        studentSet.add(new Student(1,"Vishal"));
//        System.out.println(studentSet);



//        Set<Student> studentSet = new HashSet<>();
//        Set<Student> studentSet = new LinkedHashSet<>();
//        Set<Student> studentSet = new TreeSet<>();
//        Set<Student> studentSet = new TreeSet<>(Comparator.reverseOrder());
        Set<Student> studentSet = new TreeSet<>((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        studentSet.add(new Student(4,"xyz"));
        studentSet.add(new Student(1,"abc"));
        studentSet.add(new Student(12,"bcd"));
        studentSet.add(new Student(13,"kcd"));
        studentSet.add(new Student(11,"efg"));
        studentSet.add(new Student(14,"lcd"));
        studentSet.add(new Student(24,"gcd"));
        for(Student student: studentSet){
            System.out.println(student);
        }


    }
}
