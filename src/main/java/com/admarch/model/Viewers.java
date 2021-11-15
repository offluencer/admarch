package com.admarch.model;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Viewer", catalog = "admarch")
public class Viewers implements Serializable {


    @Column(name = "rideId")
    private String rideId;

    @Id
    @Column(name = "rideId")
    private String viewerId;

    @Column(name = "ageRange")
    private String ageRange;

    @Column(name = "gender")
    private String gender;

    @Column(name = "avgAge")
    private int avgAge;
}
