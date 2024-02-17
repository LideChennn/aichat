package cn.edu.gdpu.aichat.service;


import cn.edu.gdpu.aichat.dto.UserDto;
import cn.edu.gdpu.aichat.entity.User;

public interface UserService {

    /**
     * 登录
     */
    User login(UserDto user);


    void signup(UserDto userDto);

    User getByUserId();
}
