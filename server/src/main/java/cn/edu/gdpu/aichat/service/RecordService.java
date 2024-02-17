package cn.edu.gdpu.aichat.service;


import cn.edu.gdpu.aichat.entity.ChatRecord;

import java.util.List;

public interface RecordService {
    /**
     * 根据历史id获取聊天记录（联合ai回复表）
     * @param historyId 历史id
     * @return List<ChatRecord>
     */
    List<ChatRecord> getRecordAndAiResponseByHistoryId(Integer historyId);

    /**
     * 存入用户发送的聊天内容
     * @param chatRecord 用户发送的聊天内容
     */
    void insertRecordByObject(ChatRecord chatRecord);
}
