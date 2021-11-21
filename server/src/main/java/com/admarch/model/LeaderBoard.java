package com.admarch.model;

import lombok.Data;

@Data
public class LeaderBoard {
    String registerNumber;
    long actionEarning;
    public LeaderBoard(String registerNumber, long actionEarning){
        this.registerNumber = registerNumber;
        this.actionEarning = actionEarning;
    }
}
