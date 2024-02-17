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
@ApiModel(description = "聊天记录相关数据")
public class ChatRecord implements Serializable {
    @ApiModelProperty("聊天记录ID")
    private Integer recordId;
    @ApiModelProperty("历史记录ID")
    private Integer historyId;
    @ApiModelProperty("聊天记录具体内容")
    private String content;
    @ApiModelProperty("聊天记录具体内容对应的AI回复")
    private AiResponse aiResponse;
}