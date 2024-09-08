package com.engapp.UserService.controller.admin;

import com.engapp.UserService.dto.request.RoleRequest;
import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.RoleResponse;
import com.engapp.UserService.mapper.RoleMapper;
import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.service.PermissionService;
import com.engapp.UserService.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionService permissionService;

    @PostMapping(value = "/")
    public ApiStructResponse<RoleResponse> addRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleMapper.roleRequestToRole(roleRequest);
        Set<Permission> permissions = new HashSet<>();
        for(Permission permission : role.getPermissions()) {
            Permission p = this.permissionService.getPermissionById(permission.getId());
            permissions.add(p);
        }
        role.setPermissions(permissions);
        Role rolePersist = this.roleService.addRole(role);
        RoleResponse roleResponse = this.roleMapper.roleToRoleResponse(rolePersist);
        return ApiStructResponse.<RoleResponse>builder().message("Test").data(roleResponse).build();
    }

    @GetMapping(value = "/")
    public ApiStructResponse<List<RoleResponse>> getRole() {
        ApiStructResponse<List<RoleResponse>> roleApiStructResponse = new ApiStructResponse<>();
        List<Role> listRoles = this.roleService.getAllRoles();
        List<RoleResponse> roleResponses = listRoles.stream().map(role -> roleMapper.roleToRoleResponse(role)).toList();

        roleApiStructResponse.setCode(2001);
        roleApiStructResponse.setMessage("Get role successfully !");
        roleApiStructResponse.setData(roleResponses);
        return roleApiStructResponse;
    }

    @DeleteMapping("/{roleId}")
    public ApiStructResponse<String> deleteRole(@PathVariable int roleId) {
        Role role = this.roleService.getRoleById(roleId);

        this.roleService.deleteRole(role);
        return ApiStructResponse.<String>builder()
                .message("Delete role successfully !")
                .data(role.getId() + "")
                .build();
    }
}
