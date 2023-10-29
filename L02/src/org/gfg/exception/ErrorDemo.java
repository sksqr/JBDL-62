package org.gfg.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorDemo {

    private static List<Date> list = new ArrayList<>();

    public static void main(String[] args) {
        createObjects();
       // printData("Test Stack");
    }

    //stackOverFlowError
    private static void printData(String data){
        System.out.println(data);
        printData(data);
    }

    private static void createObjects(){
        while (true){
            list.add(new Date());
        }
    }



}
