package com.admarch.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RideDetails implements Serializable {
    Rides rides;
    List<Viewers> viewers;
}
