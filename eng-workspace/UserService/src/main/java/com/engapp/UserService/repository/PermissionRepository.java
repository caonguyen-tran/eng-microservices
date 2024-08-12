package com.engapp.UserService.repository;

import com.engapp.UserService.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    public Optional<Permission> findByName(String name);

    public Optional<Permission> findById(int id);

    public boolean existsByName(String name);
}
