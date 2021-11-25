package com.admarch.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
public class Viewer implements Serializable {
    private String gender;
    private String avgAge;
}
