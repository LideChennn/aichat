package cn.edu.gdpu.aichat.dto;

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
@ApiModel(description = "用户登录时传递的数据模型")
public class UserDto implements Serializable {
    @ApiModelProperty("用户账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}
