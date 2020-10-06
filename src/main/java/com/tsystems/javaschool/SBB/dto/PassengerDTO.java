package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/**
 * Data access object that represents entity {@link Passenger}
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

    private int id;

    private String firstName;

    private String lastName;

    private Date birthDate;

}
