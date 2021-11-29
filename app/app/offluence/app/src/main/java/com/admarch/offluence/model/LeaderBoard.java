package com.admarch.offluence.model;

public class LeaderBoard {
    String rank;
    String registerNumber;
    String actionEarning;


    public LeaderBoard(String rank, String registerNumber, String actionEarning){
        this.rank = rank;
        this.registerNumber = registerNumber;
        this.actionEarning = actionEarning;
    }

    public String getRank() {
        return rank;
    }

    public String getActionEarning() {
        return actionEarning;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
