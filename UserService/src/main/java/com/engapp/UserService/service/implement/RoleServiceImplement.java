package com.engapp.UserService.service.implement;

import com.engapp.UserService.exception.ApplicationException;
import com.engapp.UserService.exception.ErrorCode;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.repository.RoleRepository;
import com.engapp.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImplement implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRoleById(int id) {
        return this.roleRepository.findById(id).orElseThrow(
                () -> new ApplicationException(ErrorCode.ROLE_NOT_EXISTS)
        );
    }

    @Override
    public Role getRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName).orElseThrow(
                () -> new ApplicationException(ErrorCode.ROLE_NOT_EXISTS)
        );
    }

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(this.roleRepository.findAll());
    }

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return null;
    }

    @Override
    public List<Role> getRolesByPermissionId(int permissionId) {
        return null;
    }

    @Override
    public boolean existsByName(String roleName) {
        return this.roleRepository.existsByName(roleName);
    }

    @Override
    public Role addRole(Role role) {
        if (this.existsByName(role.getName())) {
            throw new ApplicationException(ErrorCode.ROLE_EXISTS);
        }
        return this.roleRepository.save(role);
    }
}
