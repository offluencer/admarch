package com.admarch.model;

import lombok.Data;

@Data
public class LeaderBoard {
    String registerNumber;
    int actionEarning;
    public LeaderBoard(String registerNumber, int actionEarning){
        this.registerNumber = registerNumber;
        this.actionEarning = actionEarning;
    }
}
