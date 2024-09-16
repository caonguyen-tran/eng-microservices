package com.engapp.UserService.repository;

import com.engapp.UserService.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByUsername(String username);

    @Query("select u from User u where u.username = :username")
    User findByUsername(String username);
}
