package cn.edu.gdpu.aichat.service.impl;


import cn.edu.gdpu.aichat.mapper.ChatRecordMapper;
import cn.edu.gdpu.aichat.entity.ChatRecord;
import cn.edu.gdpu.aichat.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private ChatRecordMapper chatRecordMapper;

    @Override
    public List<ChatRecord> getRecordAndAiResponseByHistoryId(Integer historyId) {
        return chatRecordMapper.getRecordAndAiResponseByHistoryId(historyId);
    }

    @Override
    public void insertRecordByObject(ChatRecord chatRecord) {
        chatRecordMapper.insertRecordByObject(chatRecord);
    }
}
