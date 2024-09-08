package com.engapp.UserService.controller.admin;

import com.engapp.UserService.dto.request.PermissionRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.PermissionResponse;
import com.engapp.UserService.mapper.PermissionMapper;
import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.service.PermissionService;
import com.engapp.UserService.service.RoleService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/permissions")
@Slf4j
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionMapper permissionMapper;

    @PostMapping(value = "/")
    public ApiStructResponse<PermissionResponse> createPermission(@RequestBody @Valid PermissionRequest permissionRequest) {
        Permission permission = this.permissionMapper.permissionRequestToPermission(permissionRequest);
        Permission permissionPersist = this.permissionService.addPermission(permission);
        PermissionResponse permissionResponse = this.permissionMapper.permissionToPermissionResponse(permissionPersist);
        return ApiStructResponse.<PermissionResponse>builder()
                .message("Create permission success!")
                .data(permissionResponse)
                .build();
    }

    @GetMapping(value = "/")
    public ApiStructResponse<List<PermissionResponse>> getPermissions() {
        List<Permission> listPermissions = this.permissionService.getAllPermission();
        List<PermissionResponse> listPermissionResponses = listPermissions.stream().map(permission -> this.permissionMapper.permissionToPermissionResponse(permission)).toList();

        return ApiStructResponse.<List<PermissionResponse>>builder()
                .message("List of permissions")
                .data(listPermissionResponses)
                .build();
    }

    @DeleteMapping("/{permissionId}")
    public ApiStructResponse<String> deletePermission(@PathVariable int permissionId) {
        Permission permission = this.permissionService.getPermissionById(permissionId);
        this.permissionService.deletePermission(permission);
        return ApiStructResponse.<String>builder()
                .message("Delete permission success!")
                .data(permission.getId() + "")
                .build();
    }
}
