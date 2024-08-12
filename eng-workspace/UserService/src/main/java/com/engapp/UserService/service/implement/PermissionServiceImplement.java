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

import java.util.List;
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
        return this.permissionRepository.findByName(name).orElseThrow(
                () -> new ApplicationException(ErrorCode.PERMISSION_NOT_EXISTS)
        );
    }

    @Override
    public List<Permission> getAllPermission() {
        return this.permissionRepository.findAll();
    }

    @Override
    public boolean existsByName(String permissionName) {
        return this.permissionRepository.existsByName(permissionName);
    }

    @Override
    public void saveObject(Permission permission) {
        this.permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Permission permission) {
        this.permissionRepository.delete(permission);
    }

    @Override
    public Permission addPermission(Permission permission) {
        if (this.existsByName(permission.getName())) {
            throw new ApplicationException(ErrorCode.PERMISSION_EXISTS);
        }
        return this.permissionRepository.save(permission);
    }
}
