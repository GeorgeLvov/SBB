package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;


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

    @NotBlank(message = "*Passenger name can't be empty.")
    @Size(min = 1, max = 100, message = "*Name must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid name.")
    private String firstName;

    @NotBlank(message = "*Passenger surname can't be empty.")
    @Size(min = 1, max = 100, message = "*Surname must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid surname.")
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "*Date of birth can't be later than now")
    private LocalDate birthDate;

}
