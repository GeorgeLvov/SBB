package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.validator.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data transfer object that represents entity {@link User}
 *
 * @author George Lvov
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @EmailValidator
    @Size(min = 6, max = 50, message = "*Email must be between 6 and 50 characters.")
    private String username;

    @NotBlank(message = "*Password can't be empty.")
    @Size(min = 4, max = 50, message = "*Password must be between 4 and 50 characters.")
    private String password;

    private String confirmPassword;

    private RoleDTO roleDTO;
}
