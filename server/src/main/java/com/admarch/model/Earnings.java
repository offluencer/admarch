package com.admarch.model;

public class Earnings {
    String registerNumber;
    int conversionCount;
    int conversionEarning;
    public Earnings(String registerNumber,
            int conversionCount,
            int conversionEarning){
        this.conversionCount = conversionCount;
        this.conversionEarning = conversionEarning;
        this.registerNumber =registerNumber;

    }
}
