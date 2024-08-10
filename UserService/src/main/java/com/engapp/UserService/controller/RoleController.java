package com.engapp.UserService.controller;

import com.engapp.UserService.dto.response.ApiStructResponse;
import com.engapp.UserService.dto.response.RoleResponse;
import com.engapp.UserService.mapper.RoleMapper;
import com.engapp.UserService.pojo.Role;
import com.engapp.UserService.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping(value = "/")
    public ApiStructResponse<RoleResponse> addRole(@RequestBody Role role) {
        ApiStructResponse<RoleResponse> roleApiStructResponse = new ApiStructResponse<>();

        roleApiStructResponse.setCode(2002);
        roleApiStructResponse.setMessage("Create role successfully !");
        roleApiStructResponse.setData(roleMapper.roleToRoleResponse(this.roleService.addRole(role)));

        return roleApiStructResponse;
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
}
