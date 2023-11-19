package org.gfg.di;


import lombok.*;

@Setter
@Getter
@ToString
public class Car {
    private String name;
    private Engine engine;

    public Car() {
        System.out.println("Creating car");
        name = " Tata tiago";
        engine = new Engine("Revetron",1200);
    }

    public Car(String name, Engine engine) {
        this.name = name;
        this.engine = engine;
    }

    public void runCar(){
        System.out.println("Running Car "+name+" with engine "+engine.getName());
    }
}
