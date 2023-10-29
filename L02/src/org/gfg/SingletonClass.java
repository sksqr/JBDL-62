package org.gfg;

public class SingletonClass {
    private static final SingletonClass obj = new SingletonClass();
    private SingletonClass() {
    }

    public static SingletonClass getInstance(){
        return obj;
    }
}
