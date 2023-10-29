package org.gfg;

public class SingletonDemo {

    public static void main(String[] args) {
        SingletonClass obj1 = SingletonClass.getInstance();
        SingletonClass obj2 = SingletonClass.getInstance();
        if(obj2 == obj1){
            System.out.println("Same object");
        }
        else{
            System.out.println("different object");
        }

    }
}
