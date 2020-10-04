package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(uses = {RoleMapper.class})
@Component
public interface UserMapper {
    @Mappings({
            @Mapping(target="roleDTO", source="user.role")
    })
    UserDTO toDTO(User user);
    @Mappings({
            @Mapping(target="role", source="userDTO.roleDTO")
    })
    User toEntity(UserDTO userDTO);


    List<UserDTO> toDTOList(List<User> userList);

    List<User> toEntityList(List<UserDTO> userDTOList);
}
