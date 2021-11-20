package com.admarch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Actions", catalog = "admarch")
public class Actions implements Serializable {


    @Column(name = "actionId")
    private String actionId;

    @Id
    @Column(name = "registerNumber")
    private String registerNumber;

    @Column(name = "actionDateTime")
    private String actionDateTime;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "actionEarning")
    private int actionEarning;
}
