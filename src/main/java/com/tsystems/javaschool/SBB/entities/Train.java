package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 20, message = "Min name length is 3, max is 20")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Latin letters, digits and hyphens are allowed")
    @Column(name = "name")
    private String trainName;

    @Column(name = "capacity")
    private int capacity;

}