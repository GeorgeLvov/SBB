package com.tsystems.javaschool.SBB.entities;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "trip")
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   /* @Column(name = "train_id")
    private Train train;

    @Column(name = "route_id")
    private Route route;*/

    @Column(name = "departure_time")
    private Date departureTime;
}
