package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ticket")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "valid")
    private boolean isValid;
}
