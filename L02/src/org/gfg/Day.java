package org.gfg;

public enum Day {

    SUN("SUN",1,"SUNDAY"),MON("MON",2,"MONDAY");

    private String value;
    private int dayOfWeek;

    private String fullName;

    Day(String value, int dayOfWeek, String fullName) {
        this.value = value;
        this.dayOfWeek = dayOfWeek;
        this.fullName = fullName;
    }

//    private Day(String value, int dayOfWeek) {
//        this.value = value;
//        this.dayOfWeek = dayOfWeek;
//    }
}
