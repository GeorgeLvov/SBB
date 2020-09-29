package com.tsystems.javaschool.SBB.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
