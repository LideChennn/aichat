package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.mapper.AiResponseMapper;
import cn.edu.gdpu.aichat.entity.AiResponse;
import cn.edu.gdpu.aichat.service.AiResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiResponseServiceImpl implements AiResponseService {
    @Autowired
    private AiResponseMapper aiResponseMapper;

    @Override
    public void insertAiResponseByObject(AiResponse aiResponse) {
        aiResponseMapper.insertSelective(aiResponse);
    }
}
