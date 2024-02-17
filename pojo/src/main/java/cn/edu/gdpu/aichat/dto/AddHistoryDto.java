package cn.edu.gdpu.aichat.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户添加历史记录名称所传输的数据")
public class AddHistoryDto implements Serializable {
    @ApiModelProperty("用户自定义AI提示词")
    private String userAiPrompt;
    @ApiModelProperty("历史记录名称")
    private String historyName;
    @ApiModelProperty("AI提示词系统预设的ID")
    private Integer promptId;
}
