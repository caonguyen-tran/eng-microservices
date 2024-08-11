package com.engapp.UserService.service;

import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;

import java.util.List;
import java.util.Set;

public interface PermissionService {

    public Permission getPermissionById(int id);

    public Permission getPermissionByName(String name);

    public List<Permission> getAllPermission();

    public Permission addPermission(Permission permission);

    public boolean existsByName(String permissionName);

    public void saveObject(Permission permission);

    public void deletePermission(Permission permission);
}
