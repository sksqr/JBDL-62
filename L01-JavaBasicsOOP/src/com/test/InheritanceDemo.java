package com.test;

import java.util.Date;
import java.util.List;

public class InheritanceDemo {
    public static void main(String[] args) {

        Lecture l3 = new Lecture("Exception handling", new Date(),"upcomming","ShashiKant");
        System.out.println(l3.toString());




        Teacher t1 = new Teacher();
        t1.setName("Ravi");
        t1.setSubject("Maths");
        t1.walk();
//Subclass to superclass
        Person p1 = new Teacher();
        p1.setName("Ravi");
        //p1.setSubject("Maths");
        p1.walk();

        //Superclass to subclass
        if(p1 instanceof Teacher) {
            Teacher teacher = (Teacher) p1;
            teacher.setName("Ravi");
            teacher.setSubject("Maths");
            teacher.setAge(21);
            teacher.walk();
        }


        //Superclass to subclass Not Possible
//        Teacher teacher = (Teacher) new Person();
//        teacher.setName("Ravi");
//        teacher.setSubject("Maths");
//        teacher.setAge(21);
//        teacher.walk();



    }

    public String processPerson(Person person){
        //
        //
        return "Done";
    }

    public String processAllPerson(List<Person> list){
        //
        //
        for(Person p : list){
            p.walk();
        }
        return "Done";
    }
}
