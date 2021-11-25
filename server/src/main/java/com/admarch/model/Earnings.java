package com.admarch.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class Earnings implements Serializable {
    String registerNumber;
    long conversionCount;
    long conversionEarning;

    public Earnings(){}
    public Earnings(String registerNumber,
            long conversionCount,
            long conversionEarning){

        this.conversionCount = Objects.isNull(conversionCount) ? 0 : conversionCount;
        this.conversionEarning = Objects.isNull(conversionEarning) ? 0 : conversionEarning;
        this.registerNumber =Objects.isNull(registerNumber) ? "" : registerNumber;

    }
}
