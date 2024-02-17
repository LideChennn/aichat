package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.context.BaseContext;
import cn.edu.gdpu.aichat.dto.Message;
import cn.edu.gdpu.aichat.service.ChatGPTService;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    @Autowired
    private OpenAiService openAiService;
    @Value("${openai.chatgpt.model}")
    private String model;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public String getChatGPTResponse(List<ChatMessage> list) {
        //封装请求信息
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model(model)
                .messages(list)
                .build();
        // chatCompletion 对象就是chatGPT响应的数据了,
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(completionRequest);

        //解封数据
        List<ChatCompletionChoice> choices = chatCompletion.getChoices();
        ChatMessage message = choices.get(0).getMessage();
        return message.getContent();
    }

    @Override
    public String getChatGPTResponseUseStream(Integer userId, List<ChatMessage> list) {
        //封装请求信息
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model(model)
                .messages(list)
                .stream(true)
                .build();
        StringBuffer sb = new StringBuffer();
        openAiService.streamChatCompletion(completionRequest)
                .blockingForEach(e -> {
                    String content = e.getChoices().get(0).getMessage().getContent();
                    if (content != null){
                        String dest = "/user/" + userId + "/messages";
                        messagingTemplate.convertAndSend(dest, new Message(content));
                        sb.append(content);
                    }
                });
        return sb.toString();
    }
}
