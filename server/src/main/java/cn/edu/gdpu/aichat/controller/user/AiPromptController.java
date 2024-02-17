package cn.edu.gdpu.aichat.controller.user;


import cn.edu.gdpu.aichat.entity.AiPrompt;
import cn.edu.gdpu.aichat.result.Result;
import cn.edu.gdpu.aichat.service.AiPromptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prompt")
@Api(tags = "系统预设提示词相关接口")
@Slf4j
public class AiPromptController {

    @Autowired
    private AiPromptService aiPromptService;

    /**
     * 获取所有的prompt，存到session
     * @return .
     */
    @GetMapping("/")
    @ApiOperation("获得所有的Prompt")
    public Result<List<AiPrompt>> getAllPrompt() {
        log.info("获得所有的Prompt");
        List<AiPrompt> prompts = aiPromptService.getAllPrompt();
        return Result.success(prompts);
    }
}
