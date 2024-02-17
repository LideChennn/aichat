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
@ApiModel(description = "用户修改历史记录名称所传输的数据")
public class UpdateHistoryDto implements Serializable {
    @ApiModelProperty("用户账号")
    private Integer historyId;
    @ApiModelProperty("密码")
    private String historyName;
}
