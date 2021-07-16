package com.shop.egs.controller;

import com.shop.egs.dto.LoginDto;
import com.shop.egs.dto.UserDto;
import com.shop.egs.model.User;
import com.shop.egs.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserController.ROUTE)
public class UserController {
    final static String ROUTE="/user";

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto signUp(@RequestBody UserDto userDto) throws Exception{
        return service.signUp(userDto);
    }
    @PostMapping("/signin")
    public void signIn(@RequestBody LoginDto loginDto) throws Exception {
        service.signIn(loginDto.getUserName(),loginDto.getPassword());
    }
    @PostMapping("/signout")
    public void signOut(@RequestBody LoginDto loginDto) throws Exception {
        service.signOut(loginDto.getUserName(),loginDto.getPassword());
    }
    @GetMapping("/getuser")
    public ResponseEntity<UserDto> signOut(@RequestParam("username")String userName) throws Exception {
        return new ResponseEntity<>(service.getUserInfo(userName), HttpStatus.OK);

    }
}
