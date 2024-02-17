package cn.edu.gdpu.aichat.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户登录返回的数据格式")
public class UserVo implements Serializable {
    @ApiModelProperty("用户id")
    private Integer userId;
    @ApiModelProperty("用户账号")
    private String username;
    @ApiModelProperty("jwt认证")
    private String authentication;

}
