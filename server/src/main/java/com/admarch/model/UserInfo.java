package com.admarch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "UserInfo", catalog = "admarch")
public class UserInfo {

    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "ipAddress")
    private String ipAddress;
}
