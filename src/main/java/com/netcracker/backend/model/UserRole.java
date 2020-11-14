package com.netcracker.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "mo_user_role", schema = "momentum")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
