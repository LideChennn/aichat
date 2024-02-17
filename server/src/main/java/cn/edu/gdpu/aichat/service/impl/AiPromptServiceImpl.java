package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.mapper.AiPromptMapper;
import cn.edu.gdpu.aichat.entity.AiPrompt;
import cn.edu.gdpu.aichat.service.AiPromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiPromptServiceImpl implements AiPromptService {

    @Autowired
    private AiPromptMapper aiPromptMapper;
    /**
     * 获取所有的prompt
     * @return List<AiPrompt>
     */
    @Override
    public List<AiPrompt> getAllPrompt() {
        return aiPromptMapper.selectByExample(null);
    }
}
