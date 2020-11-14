package com.netcracker.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "mo_users", schema = "momentum")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String email;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private String profileDescription;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private UserStatus status;

    @JsonIgnore
    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "mo_user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
    private Set<User> subscribers = new HashSet<>();

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "mo_user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    private Set<User> subscriptions = new HashSet<>();
}
