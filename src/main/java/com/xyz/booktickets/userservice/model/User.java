package com.xyz.booktickets.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Entity
@Table(name = "user_table", schema = "users")
@Getter @Setter
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserTypeEnum userType;

    public User() {
    }

    public User(String userName, String password, String emailId, UserTypeEnum userType) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.userType = userType;
    }

}
