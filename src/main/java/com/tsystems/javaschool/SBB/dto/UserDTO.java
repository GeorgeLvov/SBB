package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data access object that represents entity {@link User}
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
    private String username;
    private String password;
    private String confirmPassword;
    private RoleDTO roleDTO;
}
