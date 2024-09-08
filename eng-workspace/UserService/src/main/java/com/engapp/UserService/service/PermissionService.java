package com.engapp.UserService.service;

import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    Permission getPermissionById(int id);

    Permission getPermissionByName(String name);

    List<Permission> getAllPermission();

    Permission addPermission(Permission permission);

    boolean existsByName(String permissionName);

    void saveObject(Permission permission);

    void deletePermission(Permission permission);
}
