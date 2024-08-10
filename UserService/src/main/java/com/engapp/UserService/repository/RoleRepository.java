package com.engapp.UserService.repository;

import com.engapp.UserService.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Optional<Role> findByName(String roleName);

    @Query("select c from Role c join User u where c.id = :userId")
    public Optional<Set<Role>> findRolesByUserId(int userId);

    public boolean existsByName(String roleName);
}
