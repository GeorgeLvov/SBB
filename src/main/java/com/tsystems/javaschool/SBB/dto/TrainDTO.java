package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Train;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "*This field can't be empty.")
    @Size(min = 3, max = 30, message = "*Name must be between 3 and 30 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "*Invalid symbol (only spaces or hyphens are allowed).")
    private String trainName;

    @Range(min = 1, max = 1000, message = "*Capacity must be between 1 and 1000 seats.")
    private int capacity;

}
