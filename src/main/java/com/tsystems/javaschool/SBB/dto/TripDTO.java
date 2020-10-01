package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Trip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data access object that represents entity {@link Trip}
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private int id;
    private String name;
    private TrainDTO trainDTO;

}
