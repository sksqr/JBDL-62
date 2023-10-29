package com.test.interfacedemo;

public interface IndGovtNomsForCar {
     public String getRC();
     String getInsurance();

     String getCountry();

     default String getLocation(){
          return null;
     }
}
