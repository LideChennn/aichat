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
@ApiModel(description = "历史记录")
public class History implements Serializable {
    @ApiModelProperty("历史记录ID")
    private Integer historyId;
    @ApiModelProperty("历史记录名称")
    private String historyName;
    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("Ai系统预设提示词")
    private AiPrompt aiPrompt;
    @ApiModelProperty("用户自定义提示词")
    private String userAiPrompt;
    @ApiModelProperty("Ai系统预设提示词ID")
    private Integer promptId;
}