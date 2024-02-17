package cn.edu.gdpu.aichat.service;

import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public interface ChatGPTService {
    /**
     * 根据[消息列表]获取chatgpt信息
     * @param list 消息队列
     * @return ai回复
     */
    String getChatGPTResponse(List<ChatMessage> list);

    /**
     * 根据[消息列表]获取chatgpt信息,但是通过流
     * @param list 消息队列
     * @return ai回复
     */
    String getChatGPTResponseUseStream(Integer userId, List<ChatMessage> list);
}
