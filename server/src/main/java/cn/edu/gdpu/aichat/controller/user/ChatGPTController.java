package cn.edu.gdpu.aichat.controller.user;


import cn.edu.gdpu.aichat.context.BaseContext;
import cn.edu.gdpu.aichat.dto.Message;
import cn.edu.gdpu.aichat.result.Result;
import cn.edu.gdpu.aichat.service.ChatGPTService;
import com.theokanning.openai.completion.chat.ChatMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/chat")
@Api(tags = "OpenAi静态调用相关接口")
@Slf4j
public class ChatGPTController {
    @Autowired
    private ConcurrentMap<Integer, List<ChatMessage>> chatMessagesMap;

    @Autowired
    private ChatGPTService chatGPTService;

    @Value("${openai.chatgpt.systemRole}")
    private String systemRole;

    /**
     *
     * @param msg 用户发送的聊天信息
     * @return 聊天消息列表为空，返回错误码
     */
    @PostMapping("/doChat")
    @ApiOperation("根据用户发生的聊天信息，获取AI的回复")
    public Result doChat(@RequestBody Message msg) {
        log.info("根据用户发生的聊天信息，获取AI的回复,{}", msg);
        List<ChatMessage> chatMessages = chatMessagesMap.get(BaseContext.getCurrentId());
        log.info("聊天上下文,{}", chatMessages);

        //聊天消息列表不存在则返回错误信息
        if (chatMessages  == null || chatMessages.size() == 0) {
            return Result.error("fail");
        }
        chatMessages.add(new ChatMessage("user", msg.getContent()));
        String gptResponse = chatGPTService.getChatGPTResponse(chatMessages);
        chatMessages.add(new ChatMessage("assistant", gptResponse));
        return Result.success(gptResponse);
    }
}