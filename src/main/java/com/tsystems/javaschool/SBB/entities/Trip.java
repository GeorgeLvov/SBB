package com.tsystems.javaschool.SBB.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "trip")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "departure_time")
    private Date departureTime;

    @OneToMany
    private List<Ticket> tickets;

    public Trip(Train train, Route route, Date departureTime) {
        this.train = train;
        this.route = route;
        this.departureTime = departureTime;
    }
}
