package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "station")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String stationTitle;

}
