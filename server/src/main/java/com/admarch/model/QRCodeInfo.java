package com.admarch.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "QRCodeInfo", catalog = "admarch")
public class QRCodeInfo {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "infuencerRegNo")
    private String influencerRegNo;

    @Column(name = "campaignId")
    private int campaignId;
}
