package com.engapp.UserService.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String Password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate = LocalDateTime.now();

    @ManyToMany
    private Set<Role> roles;

    @Column(name="picture")
    private String picture;

    @Column(name="provider")
    private String provider;

    @Column(name="provider_id")
    private String providerId;

    @Column(name="enabled")
    private Boolean enabled = true;
}
