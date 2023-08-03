package com.bank.authorization.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "public")
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Max(40)
    private String role;

    @Column(name = "profile_id")
    private int profileId;

    @Max(500)
    private String password;

}
