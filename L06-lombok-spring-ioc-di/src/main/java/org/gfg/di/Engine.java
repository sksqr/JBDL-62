package org.gfg.di;

import lombok.*;



@Setter
@Getter

@ToString
public class Engine {
    private String  name;
    private Integer cc;

    public Engine() {
        System.out.println("Creating Engine object");
    }

    public Engine(String name, Integer cc) {
        System.out.println("Creating Engine object with all agr constructor");
        this.name = name;
        this.cc = cc;
    }
    public void engineInit(){
        System.out.println("Executing engineInit for "+name);
    }

    public void engineDestroy(){
        System.out.println("Executing engineDestroy for "+name);
    }
}
