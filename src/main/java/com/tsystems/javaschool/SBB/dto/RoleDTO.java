package com.tsystems.javaschool.SBB.dto;

import com.tsystems.javaschool.SBB.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data access object that represents entity {@link Role}
 *
 * @author George Lvov
 * @version 1.0
 */


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

    private int id;
    private String roleName;

    @Override
    public String toString() {
        return roleName;
    }
}
