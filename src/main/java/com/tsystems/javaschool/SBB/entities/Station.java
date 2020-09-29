package com.tsystems.javaschool.SBB.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

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

    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Min name length is 3, max is 50")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "Latin letters, digits, hyphens, spaces are allowed")
    @Column(name = "name")
    private String stationTitle;

    @OneToMany(mappedBy = "stationFrom")
    private List<Schedule> directionsFrom;

    @OneToMany(mappedBy = "stationTo")
    private List<Schedule> directionsTo;

}
