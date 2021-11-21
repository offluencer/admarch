package com.admarch.model;

public class Earnings {
    String registerNumber;
    long conversionCount;
    long conversionEarning;

    public Earnings(String registerNumber,
            long conversionCount,
            long conversionEarning){
        this.conversionCount = conversionCount;
        this.conversionEarning = conversionEarning;
        this.registerNumber =registerNumber;

    }
}
