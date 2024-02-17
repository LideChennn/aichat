package cn.edu.gdpu.aichat.service;


import cn.edu.gdpu.aichat.entity.AiPrompt;

import java.util.List;

public interface AiPromptService {

    /**
     * 获取所有的prompt
     * @return List<AiPrompt>
     */
    List<AiPrompt> getAllPrompt();
}
