package com.tsystems.javaschool.SBB.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "station")
@Data

public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

}
