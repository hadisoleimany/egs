package com.shop.egs.service;

import com.shop.egs.BusinessException;
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

    public UserDto signUp(UserDto userDto) {
        if (rp.existsByNameAndUserName(userDto.getName(), userDto.getUserName())) {
            throw new BusinessException("User Already Exist");
        }
        User save = rp.save(new User(userDto));

        return new UserDto(save);
    }

    public void signIn(String userName, String password) {
        signInOrOut(userName, password, true);
    }

    public void signOut(String userName, String password) {
        signInOrOut(userName, password, false);
    }

    private void signInOrOut(String userName, String password, boolean loggedIn)  {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("UserName Or Password Is Wrong");
        }
        user.setLoggedIn(loggedIn);
        rp.save(user);
    }

    public UserDto getUserInfo(String userName) {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        return new UserDto(user);
    }
    public Long getUserIdByUserDto(String userName,String name,String code){
        Long user = rp.getUserIdByUserNameAndNameAndCode(userName,name,code);
        if(user==null){
             throw new BusinessException("User Does Not Exist");
        }
        return user;
    }
    public void blockUser(String userName) {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if(!user.isBlock()){
            user.setBlock(true);
            rp.save(user);
        }
    }
    public void unblockUser(String userName)  {
        User user = rp.findByUserName(userName).orElseThrow(() -> new BusinessException("User Does Not Exist"));
        if(user.isBlock()){
            user.setBlock(false);
            rp.save(user);
        }
    }
}

