package com.engapp.UserService.service.implement;

import com.engapp.UserService.exception.ApplicationException;
import com.engapp.UserService.exception.ErrorCode;
import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.repository.PermissionRepository;
import com.engapp.UserService.repository.RoleRepository;
import com.engapp.UserService.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImplement implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Permission getPermissionById(int id) {
        return this.permissionRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTS)
        );
    }

    @Override
    public Permission getPermissionByName(String name) {
        return null;
    }

    @Override
    public Set<Permission> getAllPermission() {
        return Set.of();
    }

    @Override
    public Permission addPermission(Permission permission, Role role) {
        this.permissionRepository.save(permission);
        role.getPermissions().add(permission);
        this.roleRepository.save(role);
        return permission;
    }
}
