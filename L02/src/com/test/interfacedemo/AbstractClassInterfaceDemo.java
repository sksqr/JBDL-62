package com.test.interfacedemo;

public class AbstractClassInterfaceDemo {

    public static void main(String[] args) {
        ChildClass obj = new ChildClass();
        System.out.println(obj.getData());
    }
}

abstract class Aa{
    public String getData()
    {
        return "Data from class Aa";
    }

}

// class Aa{
//    public String getData()
//    {
//        return "Data from class Aa";
//    }

//    public abstract String getData();
//}

interface I{
    default String getData(){
        return "Data from interface I";
    }
}
class ChildClass extends Aa implements I{
    public String simpleMethod(){
        return "Data";
    }
}
