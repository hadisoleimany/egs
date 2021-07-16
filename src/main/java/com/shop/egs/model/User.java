package com.shop.egs.model;

import com.shop.egs.dto.UserDto;
import com.shop.egs.enums.RoleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class User {


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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String family;
    private String code;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private RoleType role;
    private boolean loggedIn;
    private boolean block;
}
