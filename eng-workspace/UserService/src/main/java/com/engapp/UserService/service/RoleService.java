package com.engapp.UserService.service;

import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role getRoleById(int id);

    Role getRoleByName(String roleName);

    List<Role> getAllRoles();

    List<Role> getRolesByUserId(int userId);

    List<Role> getRolesByPermissionId(int permissionId);

    boolean existsByName(String roleName);

    Role addRole(Role role);

    void deleteRole(Role role);
}
