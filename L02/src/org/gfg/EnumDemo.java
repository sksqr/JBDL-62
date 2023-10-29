package org.gfg;

public class EnumDemo {
    public static void main(String[] args) {
        Day day = Day.MON;
        if(day.equals(Day.SUN)){
            System.out.println("Its a Holiday");
        }
    }
}
