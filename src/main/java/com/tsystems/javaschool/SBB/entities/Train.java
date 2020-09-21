package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "train")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String trainName;

    @Column(name = "capacity")
    private int capacity;

    @OneToMany(mappedBy = "train")
    private List<Trip> trips;

    public Train(String trainName, int capacity) {
        this.trainName = trainName;
        this.capacity = capacity;
    }
}