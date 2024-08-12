package com.engapp.UserService.mapper;


import com.engapp.UserService.dto.request.PermissionRequest;
import com.engapp.UserService.dto.response.PermissionResponse;
import com.engapp.UserService.pojo.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission permissionRequestToPermission(PermissionRequest permissionRequest);

    PermissionResponse permissionToPermissionResponse(Permission permission);
}
