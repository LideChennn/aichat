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
@ApiModel(description = "AI系统预设提示词实体类")
public class AiPrompt implements Serializable {
    @ApiModelProperty("系统预设提示词ID")
    private Integer promptId;
    @ApiModelProperty("系统预设提示词用的gpt模型，默认gpt3.5")
    private String model;
    @ApiModelProperty("系统预设提示词内容")
    private String content;
    @ApiModelProperty("系统预设提示词摘要")
    private String label;
}