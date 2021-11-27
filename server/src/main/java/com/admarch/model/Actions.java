package com.admarch.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Actions", catalog = "admarch")
public class Actions implements Serializable {
    @Id
    @Generated
    @Column(name = "actionId")
    private int actionId;

    @Column(name = "registerNumber")
    private String registerNumber;

    @Column(name = "actionDateTime")
    private Date actionDateTime;

    @Column(name = "deviceId")
    private String deviceId;

    @Column(name = "actionEarning")
    private int actionEarning;

    @Column(name = "campaignId")
    private int campaignId;
}
