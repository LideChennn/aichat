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
@ApiModel(description = "AI回复相关数据")
public class AiResponse implements Serializable {
    @ApiModelProperty("AI回复记录的ID")
    private Integer aiResponseId;
    @ApiModelProperty("AI回复记录对应的用户提问记录的ID")
    private Integer recordId;
    @ApiModelProperty("AI回复记录的内容")
    private String content;
}
