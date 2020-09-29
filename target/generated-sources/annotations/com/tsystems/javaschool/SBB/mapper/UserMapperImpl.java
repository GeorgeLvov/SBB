package com.tsystems.javaschool.SBB.mapper;

import com.tsystems.javaschool.SBB.dto.RoleDTO;
import com.tsystems.javaschool.SBB.dto.RoleDTO.RoleDTOBuilder;
import com.tsystems.javaschool.SBB.dto.UserDTO;
import com.tsystems.javaschool.SBB.dto.UserDTO.UserDTOBuilder;
import com.tsystems.javaschool.SBB.entities.Role;
import com.tsystems.javaschool.SBB.entities.Role.RoleBuilder;
import com.tsystems.javaschool.SBB.entities.User;
import com.tsystems.javaschool.SBB.entities.User.UserBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-29T23:20:20+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.username( userDTO.getUsername() );
        user.password( userDTO.getPassword() );
        user.confirmPassword( userDTO.getConfirmPassword() );
        user.role( roleDTOToRole( userDTO.getRole() ) );

        return user.build();
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.username( user.getUsername() );
        userDTO.password( user.getPassword() );
        userDTO.confirmPassword( user.getConfirmPassword() );
        userDTO.role( roleToRoleDTO( user.getRole() ) );

        return userDTO.build();
    }

    @Override
    public List<UserDTO> toDTOList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntityList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        role.id( roleDTO.getId() );
        role.roleName( roleDTO.getRoleName() );

        return role.build();
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( role.getId() );
        roleDTO.roleName( role.getRoleName() );

        return roleDTO.build();
    }
}
