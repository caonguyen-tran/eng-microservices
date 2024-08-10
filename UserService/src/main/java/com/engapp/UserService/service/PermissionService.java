package com.engapp.UserService.service;

import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;

import java.util.Set;

public interface PermissionService {

    public Permission getPermissionById(int id);

    public Permission getPermissionByName(String name);

    public Set<Permission> getAllPermission();

    public Permission addPermission(Permission permission, Role role);
}
