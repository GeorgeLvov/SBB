package com.tsystems.javaschool.SBB.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stationindex")
    private int stationIndex;

/*    @Column(name = "route_id")
    private Route route;

    @Column(name = "station_from")
    private Station stationFrom;

    @Column(name = "station_to")
    private Station stationTo;*/

    @Column(name = "duration")
    private Long duration;

}
