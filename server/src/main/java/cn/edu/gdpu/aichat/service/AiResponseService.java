package cn.edu.gdpu.aichat.service;


import cn.edu.gdpu.aichat.entity.AiResponse;

public interface AiResponseService {

    /**
     * 存入AI回复内容
     * @param aiResponse AI回复内容
     */
    void insertAiResponseByObject(AiResponse aiResponse);
}
