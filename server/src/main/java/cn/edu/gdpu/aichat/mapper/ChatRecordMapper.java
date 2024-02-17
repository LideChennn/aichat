package cn.edu.gdpu.aichat.mapper;


import cn.edu.gdpu.aichat.entity.ChatRecord;
import cn.edu.gdpu.aichat.entity.ChatRecordExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatRecordMapper {
    /**
     * 根据历史id获取聊天记录（联合ai回复表）
     * @param historyId 历史id
     * @return List<ChatRecord>
     */
    List<ChatRecord> getRecordAndAiResponseByHistoryId(Integer historyId);

    int countByExample(ChatRecordExample example);

    int deleteByExample(ChatRecordExample example);

    int insert(ChatRecord record);

    int insertSelective(ChatRecord record);

    List<ChatRecord> selectByExampleWithBLOBs(ChatRecordExample example);

    List<ChatRecord> selectByExample(ChatRecordExample example);

    int updateByExampleSelective(@Param("record") ChatRecord record, @Param("example") ChatRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ChatRecord record, @Param("example") ChatRecordExample example);

    int updateByExample(@Param("record") ChatRecord record, @Param("example") ChatRecordExample example);

    /**
     * 存入用户发送的聊天内容
     * @param chatRecord 用户发送的聊天内容
     */
    void insertRecordByObject (ChatRecord chatRecord);
}