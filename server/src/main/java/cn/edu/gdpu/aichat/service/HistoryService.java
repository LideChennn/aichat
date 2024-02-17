package cn.edu.gdpu.aichat.service;


import cn.edu.gdpu.aichat.dto.AddHistoryDto;
import cn.edu.gdpu.aichat.dto.UpdateHistoryDto;
import cn.edu.gdpu.aichat.entity.History;

import java.util.List;

public interface HistoryService {

    /**
     * 分布查询，根据用户id获取历史记录及历史记录的prompt
     * @return List<History>
     */
    List<History> getHistoryAndAiPromptByUserId();

    /**
     * 根据历史id搜索历史
     * @param historyId 历史id
     * @return History
     */
    History getHistoryByHistoryId(Integer historyId);

    /**
     * 根据id查询用户所有的历史聊天记录
     * @return .
     */
    List<History> getAllByUserId(Integer userId);

    /**
     * 添加历史聊天记录
     * 选择性添加，具有主键返回
     */
    int insertHistoryByObject(AddHistoryDto addHistoryDto);

    /**
     * 根据historyId删除聊天记录
     * @param historyId 聊天记录id
     * @return int
     */
    int deleteHistoryByHistoryId(Integer historyId);

    /**
     * 根据userId删除所有聊天记录
     * @return int
     */
    int deleteAllHistoriesByUserId();

    /**
     * 修改historyName
     * @param updateHistoryDto 含有historyId含有historyName
     * @return Result
     */
    int updateHistoryName(UpdateHistoryDto updateHistoryDto);
}
