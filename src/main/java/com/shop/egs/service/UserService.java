package com.shop.egs.service;

import com.shop.egs.aop.AccessibleUser;
import com.shop.egs.enums.RoleType;
import com.shop.egs.exception.BusinessException;
import com.shop.egs.dto.UserDto;
import com.shop.egs.model.User;
import com.shop.egs.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class UserService {
    private final UserRepository rp;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository rp, BCryptPasswordEncoder encoder) {
        this.rp = rp;
        this.encoder = encoder;
    }

    public UserDto signUp(UserDto userDto) {
        if (rp.findByUserName(userDto.getUserName()).isPresent()) {
            throw new BusinessException("User Already Exist");
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User save = rp.save(new User(userDto));
        return new UserDto(save);
    }

    public String signIn(String userName, String password) {
        return signInOrOut(userName, password, true);
    }

    public void signOut(String userName, String password) {
        signInOrOut(userName, password, false);
    }

    private String signInOrOut(String userName, String password, boolean loggedIn) {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if (!encoder.matches(password,user.getPassword())) {
            throw new BusinessException("UserName Or Password Is Wrong");
        }
        if(user.isBlock()){
            throw new BusinessException("User Is Blocked!");
        }
        user.setLoggedIn(loggedIn);
        rp.save(user);
        return getJWTToken(userName,user.getRole());
    }
    private String getJWTToken(String username, RoleType roleType) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_"+roleType.name());

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
    public UserDto getUserInfo(String userName) {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        return new UserDto(user);
    }

    public Long getUserIdByUserDto(String userName, String name, String code) {
        Long user = rp.getUserIdByUserNameAndNameAndCode(userName, name, code);
        if (user == null) {
            throw new BusinessException("User Does Not Exist");
        }
        return user;
    }
    @AccessibleUser
    public UserDto blockUser(RoleType roleType,String userName) {
        checkPermission(roleType);
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if (!user.isBlock()) {
            user.setBlock(true);
            rp.save(user);
        }
        return new UserDto(user);
    }
    @AccessibleUser
    public UserDto unblockUser(RoleType roleType,String userName) {
        checkPermission(roleType);
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if (user.isBlock()) {
            user.setBlock(false);
            rp.save(user);
        }
        return new UserDto(user);
    }

    private void checkPermission(RoleType roleType) {
        if(roleType.equals(RoleType.USER)){
            throw new BusinessException("You Don't Have Permission");
        }
    }
}

