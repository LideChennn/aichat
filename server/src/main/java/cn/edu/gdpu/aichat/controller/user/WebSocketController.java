package cn.edu.gdpu.aichat.controller.user;

import cn.edu.gdpu.aichat.constant.AiRoleConstant;
import cn.edu.gdpu.aichat.entity.AiResponse;
import cn.edu.gdpu.aichat.entity.ChatRecord;
import cn.edu.gdpu.aichat.entity.IncomingMessage;
import cn.edu.gdpu.aichat.service.AiResponseService;
import cn.edu.gdpu.aichat.service.ChatGPTService;
import cn.edu.gdpu.aichat.service.RecordService;
import com.theokanning.openai.completion.chat.ChatMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * 这个WebSocket控制器的作用是处理客户端发送的/app/send WebSocket消息，
 * 并将处理后的消息发送给所有订阅了/topic/messages目的地的客户端。
 * <p>
 * key :userId
 * value : 聊天队列
 */
@Controller
@Api(tags = "OpenAi WebSocket流式回复相关接口")
@Slf4j
public class WebSocketController {

    @Autowired
    private ChatGPTService chatGPTService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private AiResponseService aiResponseService;

    @Autowired
    private ConcurrentMap<Integer, List<ChatMessage>> chatMessagesMap;

    @MessageMapping("/send")
//    @SendTo("/topic/messages")//广播地址
    @ApiOperation("通过websocket，流式输出openai api的回复")
    public void handleMessage(@Payload IncomingMessage message) {
        log.info("通过websocket，流式输出openai api的回复，{}",message);
        String userQuestion = message.getContent();

        if (message.getPromptId()!= null && message.getPromptId() == 4) { //如果是徐涛核心考案
            log.info("promptId:{}", message.getPromptId());
            //合并原文与用户的输入
            userQuestion = mergeTextsAndUserinput(message.getTexts(), message.getContent(), 2);
        }

        //获取用户对应的聊天记录
        List<ChatMessage> chatMessages = chatMessagesMap.get(message.getHistoryId());
        log.info("promptId:{}",chatMessages);
        chatMessages.add(new ChatMessage(AiRoleConstant.USER, userQuestion));

        //流回复
        String chatGPTResponseUseStream = chatGPTService.getChatGPTResponseUseStream(message.getUserId(), chatMessages);

        //添加到信息队列
        chatMessages.add(new ChatMessage(AiRoleConstant.ASSISTANT, chatGPTResponseUseStream));

        //TODO ai回复成功才将用户信息插入聊天表，因为ai不成功回复，用户的消息不需要保存
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setContent(message.getContent());
        chatRecord.setHistoryId(message.getHistoryId());
        recordService.insertRecordByObject(chatRecord);

        AiResponse aiResponse = new AiResponse();
        aiResponse.setContent(chatGPTResponseUseStream);
        aiResponse.setRecordId(chatRecord.getRecordId());
        aiResponseService.insertAiResponseByObject(aiResponse);

        //ai回复
        log.info("AI回复:{}", chatGPTResponseUseStream);

    }

    private String mergeTextsAndUserinput(List<String> texts, String content, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("原文如下/n-----------\n");
        for (int i = 0; i < k; i++) {
            String text = texts.get(i);
            stringBuilder.append(text).append("\n");
        }
        stringBuilder.append("--------------\n");
        stringBuilder.append(content);
        return stringBuilder.toString();
    }


}
