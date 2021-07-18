package com.shop.egs.controller;

import com.shop.egs.dto.LoginDto;
import com.shop.egs.dto.UserDto;
import com.shop.egs.enums.RoleType;
import com.shop.egs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(UserController.ROUTE)
public class UserController {
    final static String ROUTE = "/user";

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto signUp(@RequestBody UserDto userDto) {
        return service.signUp(userDto);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody LoginDto loginDto) {
        return service.signIn(loginDto.getUserName(), loginDto.getPassword());
    }

    @PostMapping("/signout")
    public void signOut(@RequestBody LoginDto loginDto)  {
        service.signOut(loginDto.getUserName(), loginDto.getPassword());
    }

    @GetMapping("/getuser")
    public ResponseEntity<UserDto> signOut(@RequestParam("username") String userName) {
        return new ResponseEntity<>(service.getUserInfo(userName), HttpStatus.OK);

    }
    @GetMapping("/block")
    public ResponseEntity<UserDto> blockUser(@RequestParam("username") String userName) {
        Optional<? extends GrantedAuthority> first = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst();
        return new ResponseEntity<>(service.blockUser(RoleType.valueOf(first.get().getAuthority().replace("ROLE_","")),userName), HttpStatus.OK);
    }
    @GetMapping("/unblock")
    public ResponseEntity<UserDto> unblockUser(@RequestParam("username") String userName) {
        Optional<? extends GrantedAuthority> first = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst();
        return new ResponseEntity<>(service.unblockUser(RoleType.valueOf(first.get().getAuthority().replace("ROLE_","")),userName), HttpStatus.OK);
    }


}
