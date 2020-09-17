package com.tsystems.javaschool.SBB.entities;

import lombok.Data;


import javax.persistence.*;


@Entity
@Table(name = "train")
@Data
public class Train {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

}