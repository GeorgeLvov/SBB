package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Table(name = "train")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String trainName;

    @Column(name = "capacity")
    private int capacity;

}