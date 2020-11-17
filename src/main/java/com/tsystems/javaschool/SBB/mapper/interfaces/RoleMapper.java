package com.tsystems.javaschool.SBB.mapper.interfaces;

import com.tsystems.javaschool.SBB.dto.RoleDTO;
import com.tsystems.javaschool.SBB.entities.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleMapper {
    Role toEntity(RoleDTO roleDTO);
    RoleDTO toDTO(Role role);
}
