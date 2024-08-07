package com.engapp.UserService.repository;

import com.engapp.UserService.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
