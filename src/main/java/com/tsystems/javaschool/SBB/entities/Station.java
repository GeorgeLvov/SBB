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
 * Simple JavaBean domain object that represents a Station.
 *
 * @author George Lvov
 * @version 1.0
 */

@Entity
@Table(name = "stations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Min name length is 3, max is 50")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "Latin letters, hyphens and spaces are allowed")
    @Column(name = "name")
    private String title;

}
