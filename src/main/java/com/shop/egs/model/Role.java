package com.shop.egs.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String description;
    @OneToMany(mappedBy = "role")
    private Set<User>  users;
}
