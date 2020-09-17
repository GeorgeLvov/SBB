package com.tsystems.javaschool.SBB.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "route")
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;
}
