package org.gfg.di;

public class DiDemo {
    public static void main(String[] args) {

        Car car1 = new Car();
        car1.runCar();
        car1.setEngine(new Engine("Max Power",1500));// DI, setter injection
        car1.runCar();

        //DI constructor injection
        Car car2 = new Car("Tata Nexon", new Engine("MAX POWER", 1500));
        car2.runCar();

    }
}
