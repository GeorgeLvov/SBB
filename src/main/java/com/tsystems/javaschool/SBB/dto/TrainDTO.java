package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data access object that represents entity {@link Train}
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    private int id;
    private String trainName;
    private int capacity;

}
