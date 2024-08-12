package com.engapp.UserService.service;

import com.engapp.UserService.pojo.Permission;
import com.engapp.UserService.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public Role getRoleById(int id);

    public Role getRoleByName(String roleName);

    public List<Role> getAllRoles();

    public List<Role> getRolesByUserId(int userId);

    public List<Role> getRolesByPermissionId(int permissionId);

    public boolean existsByName(String roleName);

    public Role addRole(Role role);

    public void deleteRole(Role role);
}
