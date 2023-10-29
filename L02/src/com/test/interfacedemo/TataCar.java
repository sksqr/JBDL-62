package com.test.interfacedemo;

import javax.xml.crypto.Data;

public abstract class TataCar implements IndGovtNomsForCar, IndEnvNorms{

    private String companyName= "Tata";
    public final String getCompanyName(){
        return companyName;
    }

    public abstract Data manufacturedDate();
}
