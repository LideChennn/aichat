package cn.edu.gdpu.aichat.controller.user;


import cn.edu.gdpu.aichat.constant.AiRoleConstant;
import cn.edu.gdpu.aichat.entity.ChatRecord;
import cn.edu.gdpu.aichat.entity.History;
import cn.edu.gdpu.aichat.result.Result;
import cn.edu.gdpu.aichat.service.HistoryService;
import cn.edu.gdpu.aichat.service.RecordService;
import com.theokanning.openai.completion.chat.ChatMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/record")
@Slf4j
@Api(tags = "聊天记录相关接口")
public class ChatRecordController {
    @Autowired
    private ConcurrentMap<Integer, List<ChatMessage>> chatMessagesMap;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RecordService recordService;

    /**
     * 根据历史记录的id获取聊天记录
     * 顺便初始化List<ChatMessage>
     * 把ChatMessage添加
     * @param historyId 前端传过来
     * @return result
     */
    @GetMapping("/{historyId}")
    @ApiOperation("根据历史记录id获取聊天记录")
    public Result<List<ChatRecord>> getRecordAndAiResponseByHistoryId(
            @ApiParam(value = "历史记录ID", required = true)
            @PathVariable Integer historyId) {
        log.info("根据历史记录id获取聊天记录, {}", historyId);
        //对话信息
        List<ChatRecord> messages = recordService.getRecordAndAiResponseByHistoryId(historyId);

        //消息List<ChatMessage>
        List<ChatMessage> chatMessages = new LinkedList<>();

        //找到历史记录的prompt
        History history = historyService.getHistoryByHistoryId(historyId);
        if (history.getUserAiPrompt() != null) {
            //如果有用户的自定义参数
            chatMessages.add(new ChatMessage(AiRoleConstant.SYSTEM, history.getUserAiPrompt()));
        } else {
            chatMessages.add(new ChatMessage(AiRoleConstant.SYSTEM, history.getAiPrompt().getContent()));
        }

        //添加聊天记录
        for (ChatRecord message : messages) {
            chatMessages.add(new ChatMessage(AiRoleConstant.USER, message.getContent()));
            chatMessages.add(new ChatMessage(AiRoleConstant.ASSISTANT, message.getAiResponse().getContent()));
        }
        //添加聊天列表消息到session
        chatMessagesMap.put(historyId,  chatMessages);
        log.info("加载到的消息列表, {}", chatMessages);

        //封装返回结果
        return Result.success(messages);
    }
}