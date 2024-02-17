package cn.edu.gdpu.aichat.controller.user;

import cn.edu.gdpu.aichat.constant.JwtClaimsConstant;
import cn.edu.gdpu.aichat.dto.UserDto;
import cn.edu.gdpu.aichat.entity.User;
import cn.edu.gdpu.aichat.properties.JwtProperties;
import cn.edu.gdpu.aichat.result.Result;
import cn.edu.gdpu.aichat.service.UserService;
import cn.edu.gdpu.aichat.utils.JwtUtil;
import cn.edu.gdpu.aichat.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录逻辑
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserVo> login(@RequestBody UserDto userDto) {
        log.info("用户登录:{}", userDto);
        User user1 = userService.login(userDto);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user1.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserVo userVo = UserVo.builder()
                .userId(user1.getUserId())
                .username(user1.getUsername())
                .authentication(token)
                .build();

        return Result.success(userVo);
    }

    /**
     * 通过Userid获取用户的信息
     */
    @GetMapping("/getUser")
    @ApiOperation("通过Userid获取用户的信息")
    public Result<User> getUserByUserId() {
        log.info("通过Userid获取用户的信息");
        User user = userService.getByUserId();
        return Result.success(user);
    }

    /**
     * 用户退出登录
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation("用户退出登录")
    public Result logout() {
        log.info("用户退出登录");
        return Result.success();
    }

    /**
     * 注册逻辑
     * @param userDto 注册信息
     * @return Result
     */
    @PostMapping("/signup")
    @ApiOperation("用户注册")
    public Result signup(@RequestBody UserDto userDto){
        log.info("用户注册,{}", userDto);
        userService.signup(userDto);
        return Result.success();
    }

}