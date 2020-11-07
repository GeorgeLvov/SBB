package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Station;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "*This field cant be empty.")
    @Size(min = 3, max = 50, message = "*Title must be between 3 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 .\\-]+$", message = "*Invalid symbol (only spaces,hyphens or dots are allowed).")
    private String title;
}
