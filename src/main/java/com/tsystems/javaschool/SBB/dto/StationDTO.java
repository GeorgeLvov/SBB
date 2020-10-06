package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Station;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data access object that represents entity {@link Station}
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {
    private int id;
    private String title;
}
