package cn.edu.gdpu.aichat.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "用户实体类")
public class User implements Serializable {
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("用户账号")
    private String username;
    @ApiModelProperty("用户密码")
    private String password;
}