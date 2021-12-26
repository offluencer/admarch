package com.admarch.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Influencer", catalog = "admarch")
public class Influencer implements Serializable {
    @Id
    @Column(name = "regNumber")
    private String regNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;
    @Column(name = "pincode")
    private String pincode;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "education")
    private String education;
    @Column(name = "monthlyIncome")
    private int monthlyIncome;
    @Column(name = "monthlyExpense")
    private int monthlyExpense;
    @Column(name = "age")
    private int age;
    @Column(name = "dailyIncome")
    private int dailyIncome;
    @Column(name = "dailyExpense")
    private int dailyExpense;
    @Column(name = "noOfDependents")
    private int noOfDependents;
    @Column(name = "loanAmount")
    private int loanAmount;
    @Column(name = "registerDate")
    private Timestamp registerDate;
    @Column(name = "type")
    private String type;
}
