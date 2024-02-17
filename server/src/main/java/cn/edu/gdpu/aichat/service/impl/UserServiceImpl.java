package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.constant.MessageConstant;
import cn.edu.gdpu.aichat.context.BaseContext;
import cn.edu.gdpu.aichat.dto.UserDto;
import cn.edu.gdpu.aichat.exception.AccountExistedException;
import cn.edu.gdpu.aichat.exception.AccountNotFoundException;
import cn.edu.gdpu.aichat.exception.PasswordErrorException;
import cn.edu.gdpu.aichat.mapper.UserMapper;
import cn.edu.gdpu.aichat.entity.User;
import cn.edu.gdpu.aichat.entity.UserExample;
import cn.edu.gdpu.aichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(UserDto userDto) {
        UserExample userExample = new UserExample();

        UserExample.Criteria criteria = userExample.createCriteria();
        //添加条件，账户密码要匹配
        criteria.andUsernameEqualTo(userDto.getUsername());
        List<User> users = userMapper.selectByExample(userExample);

        if (users == null || users.isEmpty()) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        User user1 = users.get(0);
        if (!userDto.getPassword().equals(user1.getPassword())) {
            //密码不正确
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return user1;
    }

    @Override
    public void signup(UserDto userDto) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(userDto.getUsername());
        List<User> users = userMapper.selectByExample(userExample);


        if (users.isEmpty()) {
            //没有存在用户
            //允许注册
            User user = User.builder()
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                    .build();
            userMapper.insertSelective(user);
        } else {
            //存在用户，不允许注册
            throw new AccountExistedException(userDto.getUsername() + MessageConstant.ACCOUNT_EXISTED);
        }
    }

    @Override
    public User getByUserId() {
        Integer userId = BaseContext.getCurrentId();
        return userMapper.selectById(userId);
    }
}
