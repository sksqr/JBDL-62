package com.test.interfacedemo;

import javax.xml.crypto.Data;

public class TataSafari extends TataCar{
    @Override
    public String getPUC() {
        return null;
    }

    @Override
    public String getRC() {
        return null;
    }

    @Override
    public String getInsurance() {
        return null;
    }

    @Override
    public String getCountry() {
        return "India - Safari";
    }

    @Override
    public Data manufacturedDate() {
        return null;
    }
}
