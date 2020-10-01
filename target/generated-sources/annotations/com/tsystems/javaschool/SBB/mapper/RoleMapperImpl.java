package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.RoleDTO;
import com.tsystems.javaschool.SBB.dto.RoleDTO.RoleDTOBuilder;
import com.tsystems.javaschool.SBB.entities.Role;
import com.tsystems.javaschool.SBB.entities.Role.RoleBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-02T00:08:55+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        role.id( roleDTO.getId() );
        role.roleName( roleDTO.getRoleName() );

        return role.build();
    }

    @Override
    public RoleDTO toDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( role.getId() );
        roleDTO.roleName( role.getRoleName() );

        return roleDTO.build();
    }
}
