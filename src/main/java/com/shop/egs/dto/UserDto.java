package com.shop.egs.dto;

import com.shop.egs.enums.RoleType;
import com.shop.egs.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(User user) {
        this.name = user.getName();
        this.family = user.getFamily();
        this.code = user.getCode();
        this.userName = user.getUserName();
        this.role = user.getRole();
    }

    private String name;
    private String family;
    private String code;
    private String userName;
    private String password;
    private RoleType role;

}
