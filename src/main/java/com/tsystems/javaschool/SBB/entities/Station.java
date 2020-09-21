package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "station")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String stationTitle;

    @OneToMany(mappedBy = "stationFrom")
    private List<Schedule> directionsFrom;

    @OneToMany(mappedBy = "stationTo")
    private List<Schedule> directionsTo;

    public Station(String stationTitle) {
        this.stationTitle = stationTitle;
    }
}
