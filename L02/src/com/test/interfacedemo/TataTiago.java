package com.test.interfacedemo;

import javax.xml.crypto.Data;

public class TataTiago extends TataCar{

    private String rc;
    private String puc;

    public TataTiago(String rc, String puc) {
        super();
        this.rc = rc;
        this.puc = puc;
    }

    public TataTiago() {
    }

    //    @Override
//    public String  getCompanyName(){
//        return "Tata Tiago";
//    }
    @Override
    public String getPUC() {
        return puc;
    }

    @Override
    public String getRC() {
        return rc;
    }

    @Override
    public String getInsurance() {
        return null;
    }

    @Override
    public String getCountry() {
        return "India Tiago";
    }

    @Override
    public Data manufacturedDate() {
        return null;
    }
}
