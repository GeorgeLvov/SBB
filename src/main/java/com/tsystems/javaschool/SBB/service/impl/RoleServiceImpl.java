package com.tsystems.javaschool.SBB.service.impl;

import com.tsystems.javaschool.SBB.dto.RoleDTO;
import com.tsystems.javaschool.SBB.entities.Role;
import com.tsystems.javaschool.SBB.mapper.interfaces.RoleMapper;
import com.tsystems.javaschool.SBB.repository.interfaces.RoleRepository;
import com.tsystems.javaschool.SBB.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    @Override
    @Transactional
    public RoleDTO getRoleDTOById(int id) {
        Role role = roleRepository.getRoleById(id);
        return roleMapper.toDTO(role);
    }

}
