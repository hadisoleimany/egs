package com.shop.egs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.egs.dto.UserDto;
import com.shop.egs.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User  {


    public User(String name, String family, String code, String userName, String password, RoleType role) {
        this.name = name;
        this.family = family;
        this.code = code;
        this.userName = userName;
        this.password = password;
        this.role = role;

    }
    public User(UserDto dto) {
        this.name = dto.getName();
        this.family =dto.getFamily();
        this.code = dto.getCode();
        this.userName=dto.getUserName();
        this.password=dto.getPassword();
        this.role = dto.getRole();
    }

    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String family;
    private String code;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private RoleType role;
    private boolean loggedIn;
    private boolean block;
}
