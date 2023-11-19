package org.gfg;

public class LombokDemo {

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Ravi");

        Lecture lecture1 = new Lecture();
        lecture1.setTitle("Lec06");
        lecture1.setNumber(6);
        System.out.println(lecture1);


        Lecture lecture2 = new Lecture("Lec07",7);
        System.out.println(lecture2);
        System.out.println(lecture2.getTitle());
    }
}
