package com.engapp.UserService.mapper;

import com.engapp.UserService.dto.request.RoleRequest;
import com.engapp.UserService.dto.response.RoleResponse;
import com.engapp.UserService.pojo.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleRequestToRole(RoleRequest roleRequest);

    Role roleResponseToRole(RoleResponse roleResponse);

    RoleResponse roleToRoleResponse(Role role);
}
