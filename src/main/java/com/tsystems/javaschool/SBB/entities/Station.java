package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


/**
 * Simple JavaBean domain object that represents a Station.
 *
 * @author George Lvov
 * @version 1.0
 */

@Entity
@Table(name = "station")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(name = "name")
    private String title;

}
