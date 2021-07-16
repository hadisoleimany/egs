package com.shop.egs.service;

import com.shop.egs.dto.UserDto;
import com.shop.egs.model.User;
import com.shop.egs.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository rp;

    public UserService(UserRepository rp) {
        this.rp = rp;
    }

    public UserDto signUp(UserDto userDto) throws Exception {
        if (rp.existsByNameAndUserName(userDto.getName(), userDto.getUserName())) {
            throw new Exception("User Already Exist");
        }
        User save = rp.save(new User(userDto));

        return new UserDto(save);
    }

    public void signIn(String userName, String password) throws Exception {
        signInOrOut(userName, password, true);
    }

    public void signOut(String userName, String password) throws Exception {
        signInOrOut(userName, password, false);
    }

    private void signInOrOut(String userName, String password, boolean loggedIn) throws Exception {
        User user = rp.findByUserName(userName).orElseThrow(() -> new Exception("User Does Not Exist"));
        if (!user.getPassword().equals(password)) {
            throw new Exception("UserName Or Password Is Wrong");
        }
        user.setLoggedIn(loggedIn);
        rp.save(user);
    }

    public UserDto getUserInfo(String userName) throws Exception {
        User user = rp.findByUserName(userName).orElseThrow(() -> new Exception("User Does Not Exist"));
        return new UserDto(user);
    }
    public void blockUser(String userName) throws Exception {
        User user = rp.findByUserName(userName).orElseThrow(() -> new Exception("User Does Not Exist"));
        if(!user.isBlock()){
            user.setBlock(true);
            rp.save(user);
        }
    }
    public void unblockUser(String userName) throws Exception {
        User user = rp.findByUserName(userName).orElseThrow(() -> new Exception("User Does Not Exist"));
        if(user.isBlock()){
            user.setBlock(false);
            rp.save(user);
        }
    }
}

