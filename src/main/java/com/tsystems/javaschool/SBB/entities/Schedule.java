package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stationindex")
    private int stationIndex;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "station_from")
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "station_to")
    private Station stationTo;

    @Column(name = "duration")
    private Long duration;

    public Schedule(int stationIndex, Route route, Station stationFrom, Station stationTo, Long duration) {
        this.stationIndex = stationIndex;
        this.route = route;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;
        this.duration = duration;
    }
}
