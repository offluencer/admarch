package com.admarch.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Influencer implements Serializable {


    @Id
    private String regNumber;
    private String name;
    private String city;
    private String state;
    private String area;
    private String pincode;
    private String phoneNumber;
    private String education;
    private int monthlyIncome;
    private int monthlyExpense;
    private int age;
    private int dailyIncome;
    private int dailyExpense;
    private int noOfDependents;
    private int loanAmount;




}
