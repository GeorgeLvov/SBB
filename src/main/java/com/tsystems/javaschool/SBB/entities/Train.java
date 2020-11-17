package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * JavaBean domain object that represents a Train.
 *
 * @author George Lvov
 * @version 1.0
 */

@Entity
@Table(name = "train")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(name = "name")
    private String trainName;

    @Column(name = "capacity")
    private int capacity;

}