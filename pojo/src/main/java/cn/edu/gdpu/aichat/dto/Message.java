package cn.edu.gdpu.aichat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(description = "消息对象")
public class Message implements Serializable {
    @ApiModelProperty("消息内容")
    private String content;
}
